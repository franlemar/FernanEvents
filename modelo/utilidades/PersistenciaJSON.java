package FernanEvents.modelo.utilidades;

import FernanEvents.modelo.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class PersistenciaJSON {

    private static final String ARCHIVO_USUARIOS = "datosJSON/usuarios.json";
    private static final String ARCHIVO_EVENTOS  = "datosJSON/eventos.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class,
                    (JsonSerializer<LocalDate>) (src, type, ctx) ->
                            new JsonPrimitive(src.toString()))
            .registerTypeAdapter(LocalDate.class,
                    (JsonDeserializer<LocalDate>) (json, type, ctx) ->
                            LocalDate.parse(json.getAsString()))
            .setPrettyPrinting()
            .create();

    // ------------------ GUARDAR -----------------------

    /**
     * Guarda una colección de usuarios en un archivo JSON, teniendo en cuenta el rol de cada usuario(Admin, Organizador o Asistente)
     */
    public static void guardarUsuarios(Collection<Usuario> usuarios) {
        JsonArray array = new JsonArray();
        for (Usuario u : usuarios) {
            JsonObject obj = gson.toJsonTree(u).getAsJsonObject();
            obj.addProperty("tipo", u.getRol().name());
            array.add(obj);
        }
        escribirArchivo(ARCHIVO_USUARIOS, gson.toJson(array));
    }

    /**
     * Guarda una lista de eventos en un archivo JSON, además del organizador al que corresponde cada evento
     *
     */
    public static void guardarEventos(ArrayList<Evento> eventos) {
        JsonArray array = new JsonArray();
        for (Evento e : eventos) {
            JsonObject obj = gson.toJsonTree(e).getAsJsonObject();
            if (e.getOrganizador() != null) {
                obj.getAsJsonObject("organizador")
                        .addProperty("tipo", e.getOrganizador().getRol().name());
            }
            array.add(obj);
        }
        escribirArchivo(ARCHIVO_EVENTOS, gson.toJson(array));
    }

    // --------------------CARGAR ------------------------

    /**
     * Carga los usuarios almacenados en un archivo JSON al momento de iniciar FernanEvents
     */
    public static ArrayList<Usuario> cargarUsuarios() {
        String contenido = leerArchivo(ARCHIVO_USUARIOS);
        if (contenido == null) return new ArrayList<>();

        ArrayList<Usuario> lista = new ArrayList<>();
        JsonArray array = JsonParser.parseString(contenido).getAsJsonArray();

        for (JsonElement elemento : array) {
            JsonObject obj = elemento.getAsJsonObject();
            String tipo = obj.get("tipo").getAsString();

            Usuario usuario = switch (tipo) {
                case "ADMINISTRADOR" -> gson.fromJson(obj, Administrador.class);
                case "ORGANIZADOR"   -> gson.fromJson(obj, Organizador.class);
                case "ASISTENTE"     -> gson.fromJson(obj, Asistente.class);
                default -> null;
            };

            if (usuario != null) lista.add(usuario);
        }
        return lista;
    }

    /**
     * Carga los eventos almacenador en un archivo JSON al momento de iniciar FernanEvents
     */
    public static ArrayList<Evento> cargarEventos() {
        String contenido = leerArchivo(ARCHIVO_EVENTOS);
        if (contenido == null) return new ArrayList<>();

        ArrayList<Evento> lista = new ArrayList<>();
        JsonArray array = JsonParser.parseString(contenido).getAsJsonArray();

        for (JsonElement elemento : array) {
            JsonObject obj = elemento.getAsJsonObject();

            // guardamos el organizador aparte y lo quitamos antes de que Gson lo procese
            JsonObject orgObj = null;
            if (obj.has("organizador") && !obj.get("organizador").isJsonNull()) {
                orgObj = obj.getAsJsonObject("organizador").deepCopy();
                obj.remove("organizador");
            }

            Evento evento = gson.fromJson(obj, Evento.class);

            // reconstruir el organizador con su tipo correcto
            if (orgObj != null) {
                String tipo = orgObj.get("tipo").getAsString();
                Usuario organizador = switch (tipo) {
                    case "ADMINISTRADOR" -> gson.fromJson(orgObj, Administrador.class);
                    case "ORGANIZADOR"   -> gson.fromJson(orgObj, Organizador.class);
                    case "ASISTENTE"     -> gson.fromJson(orgObj, Asistente.class);
                    default -> null;
                };
                evento.setOrganizador(organizador);
            }

            lista.add(evento);
        }
        return lista;
    }

    // -------------------HELPERS -----------------------

    /**
     * Método encargado de escribir texto en un archivo. Recibe por parámetro la ruta donde debe trabajar así como el contenido a escribir
     */
    private static void escribirArchivo(String ruta, String contenido) {
        try (Writer writer = new FileWriter(ruta)) {
            writer.write(contenido);
        } catch (IOException e) {
            System.out.println("Error al guardar " + ruta + ": " + e.getMessage());
        }
    }

    /**
     * Método encargado de leer el contenido de un archivo según la ruta que recibe por parámetro
     */
    private static String leerArchivo(String ruta) {
        File archivo = new File(ruta);
        if (!archivo.exists()) return null;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) sb.append(linea);
            return sb.toString();
        } catch (IOException e) {
            System.out.println("Error al cargar " + ruta + ": " + e.getMessage());
            return null;
        }
    }

}