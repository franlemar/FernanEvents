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

    private static final String ARCHIVO_USUARIOS = "usuarios.json";
    private static final String ARCHIVO_EVENTOS  = "eventos.json";

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

    public static void guardarUsuarios(Collection<Usuario> usuarios) {
        JsonArray array = new JsonArray();
        for (Usuario u : usuarios) {
            JsonObject obj = gson.toJsonTree(u).getAsJsonObject();
            obj.addProperty("tipo", u.getRol().name());
            array.add(obj);
        }
        escribirArchivo(ARCHIVO_USUARIOS, gson.toJson(array));
    }

    public static void guardarEventos(ArrayList<Evento> eventos) {
        escribirArchivo(ARCHIVO_EVENTOS, gson.toJson(eventos));
    }

    // --------------------CARGAR ------------------------

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

    public static ArrayList<Evento> cargarEventos() {
        String contenido = leerArchivo(ARCHIVO_EVENTOS);
        if (contenido == null) return new ArrayList<>();

        Type tipoLista = new TypeToken<ArrayList<Evento>>(){}.getType();
        return gson.fromJson(contenido, tipoLista);
    }

    // -------------------HELPERS -----------------------

    private static void escribirArchivo(String ruta, String contenido) {
        try (Writer writer = new FileWriter(ruta)) {
            writer.write(contenido);
        } catch (IOException e) {
            System.out.println("Error al guardar " + ruta + ": " + e.getMessage());
        }
    }

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