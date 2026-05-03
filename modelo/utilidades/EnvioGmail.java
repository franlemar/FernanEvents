package FernanEvents.modelo.utilidades;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

public class EnvioGmail {
    /**
     * Enviar correo con usando Gmail SMTP
     */
    public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
        String remitente = "chemamc3@gmail.com";
        String clave = "blne cdaw znbi vmtn";
        // Propiedades de la conexión que se va a establecer con el servidor de correo SMTP
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);
        props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); // Conectar de manera segura
        props.put("mail.smtp.port", "587"); // Puerto SMTP seguro de Google
        // Se obtiene la sesión en el servidor de correo
        Session session = Session.getDefaultInstance(props);
        try {
            // Creación del mensaje a enviar
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            //message.setText(cuerpo); // Para enviar texto plano
            message.setContent(cuerpo, "text/html; charset=utf-8"); // Para enviar html
            // Definición de los parámetros del protocolo de transporte
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception me) {
            me.printStackTrace();
        }
    }

    /**
     * Genera la plantilla HTML para el correo de registro de usuario
     */
    public static String plantillaRegistroUsuario(String nombreUsuario, String codigoToken) {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy 'a las' HH:mm:ss", new Locale("es", "ES"));
        String fechaHoraFormateada = ahora.format(formatter);

        return """
        <!DOCTYPE html>
        <html>
        <body style="font-family: Arial, sans-serif; background-color: #f5f7fa; padding: 20px; margin: 0;">
            <table width="100%%" align="center" style="max-width: 500px; background: white; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.08);">
                <!-- Header -->
                <tr>
                    <td style="padding: 25px 30px 20px; border-bottom: 1px solid #eaeaea;">
                        <div style="text-align: center;">
                            <h1 style="margin: 0; color: #2c3e50; font-size: 30px; font-weight: 600;">
                                <span style="color: #4d4d4d;">Fernan</span><span style="color: #4d4d4d;">Events</span>
                            </h1>
                            <p style="margin: 5px 0 0; color: #7f8c8d; font-size: 13px;">Registro de Nuevo Usuario</p>
                        </div>
                    </td>
                </tr>
        
                <!-- Content -->
                <tr>
                    <td style="padding: 30px;">
                        <p style="margin: 0 0 15px; color: #333; font-size: 15px;">
                            ¡Hola <strong style="color: #2c3e50;">%s</strong>!
                        </p>
        
                        <p style="margin: 0 0 20px; color: #555; font-size: 15px; line-height: 1.5;">
                            ¡Bienvenido a FernanEvents! Para completar tu registro y verificar tu cuenta, 
                            por favor utiliza el siguiente código de confirmación:
                        </p>
        
                        <!-- Verification Code -->
                        <div style="background: #f8f9fa; border-radius: 6px; padding: 20px; text-align: center; margin: 25px 0; border: 1px solid #eaeaea;">
                            <div style="font-size: 28px; font-weight: bold; letter-spacing: 6px; color: #4b4b4b; font-family: monospace; margin-bottom: 5px;">
                                %s
                            </div>
                            <p style="margin: 0; color: #7f8c8d; font-size: 13px;">Código de verificación de registro</p>
                        </div>
        
                        <p style="margin: 0 0 20px; color: #555; font-size: 15px; line-height: 1.5;">
                            Una vez verificado con este código que te acaba de llegar, 
                            podrás disfrutar de todos los beneficios de FernanEvents.
                        </p>
        
                        <!-- Details - Con fecha formateada -->
                        <div style="background: #f8f9fa; padding: 16px 20px; border-radius: 6px; margin-top: 20px; border-left: 4px solid #4CAF50;">
                            <p style="margin: 0 0 8px 0; font-size: 14px; color: #666; font-weight: 600;">
                                Detalles del registro:
                            </p>
                            <p style="margin: 0; font-size: 14px; color: #666;">
                                <strong>Usuario:</strong> %s<br>
                                <strong>Fecha y hora de solicitud:</strong> %s<br>
                            </p>
                        </div>
        
                        <!-- Security Note -->
                        <div style="margin-top: 25px; padding-top: 20px; border-top: 1px solid #eee;">
                            <p style="margin: 0; font-size: 13px; color: #95a5a6; line-height: 1.4;">
                            <strong>Seguridad:</strong> Este código es personal e intransferible. 
                                Si no solicitaste este registro, por favor ignora este mensaje.
                            </p>
                        </div>
                    </td>
                </tr>
        
                <!-- Footer -->
                <tr>
                    <td style="padding: 20px 30px; background: linear-gradient(135deg, #9fd7ff, #e2a6d9);; border-radius: 0 0 8px 8px;">
                        <p style="margin: 0; text-align: center; font-size: 12px; color: #2c3e50;">
                            © 2026 FernanEvents • Correo automático de registro
                        </p>
                    </td>
                </tr>
            </table>
        </body>
        </html>
        """.formatted(nombreUsuario, codigoToken, nombreUsuario, fechaHoraFormateada);
    }

    /**
     * Genera la plantilla HTML para el correo de inicio de sesion del admin
     */
    public static String plantillaLoginAdmin(String usuario, String codigo) {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy 'a las' HH:mm:ss", new Locale("es", "ES"));
        String fechaHoraFormateada = ahora.format(formatter);

        return """
        <!DOCTYPE html>
        <html>
        <body style="font-family: Arial, sans-serif; background-color: #f5f7fa; padding: 20px; margin: 0;">
            <table width="100%%" align="center" style="max-width: 500px; background: white; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.08);">
                <!-- Header -->
                <tr>
                    <td style="padding: 25px 30px 20px; border-bottom: 1px solid #eaeaea;">
                        <div style="text-align: center;">
                            <h1 style="margin: 0; color: #2c3e50; font-size: 30px; font-weight: 600;">
                                <span style="color: #4d4d4d;">Fernan</span><span style="color: #4d4d4d;">Events</span>
                            </h1>
                            <p style="margin: 5px 0 0; color: #7f8c8d; font-size: 13px;">Administración de Eventos</p>
                        </div>
                    </td>
                </tr>
        
                <!-- Content -->
                <tr>
                    <td style="padding: 30px;">
                        <p style="margin: 0 0 15px; color: #333; font-size: 15px;">
                            Hola <strong style="color: #2c3e50;">%s</strong>,
                        </p>
        
                        <p style="margin: 0 0 20px; color: #555; font-size: 15px; line-height: 1.5;">
                            Se ha detectado un intento de acceso a tu cuenta. 
                            Utiliza el siguiente código para completar la verificación:
                        </p>
        
                        <!-- Verification Code -->
                        <div style="background: #f8f9fa; border-radius: 6px; padding: 20px; text-align: center; margin: 25px 0; border: 1px solid #eaeaea;">
                            <div style="font-size: 28px; font-weight: bold; letter-spacing: 6px; color: #4b4b4b; font-family: monospace; margin-bottom: 5px;">
                                %s
                            </div>
                            <p style="margin: 0; color: #7f8c8d; font-size: 13px;">Código de verificación</p>
                        </div>
        
                        <!-- Details - Con fecha formateada -->
                        <div style="background: #f8f9fa; padding: 16px 20px; border-radius: 6px; margin-top: 20px; border-left: 4px solid #4d4d4d;">
                            <p style="margin: 0 0 8px 0; font-size: 14px; color: #666; font-weight: 600;">
                                Detalles del intento de acceso:
                            </p>
                            <p style="margin: 0; font-size: 14px; color: #666;">
                                <strong>Fecha y hora:</strong> %s<br>
                            </p>
                        </div>
        
                        <!-- Security Note -->
                        <div style="margin-top: 25px; padding-top: 20px; border-top: 1px solid #eee;">
                            <p style="margin: 0; font-size: 13px; color: #95a5a6; line-height: 1.4;">
                            <strong>Seguridad:</strong> No compartas este código. Si no reconoces esta actividad, ignora este mensaje.
                            </p>
                        </div>
                    </td>
                </tr>
        
                <!-- Footer -->
                <tr>
                    <td style="padding: 20px 30px; background: linear-gradient(135deg, #9fd7ff, #e2a6d9);; border-radius: 0 0 8px 8px;">
                        <p style="margin: 0; text-align: center; font-size: 12px; color: #2c3e50;">
                            © 2026 FernanEvents • Correo automático de seguridad
                        </p>
                    </td>
                </tr>
            </table>
        </body>
        </html>
        """.formatted(usuario, codigo, fechaHoraFormateada);
    }

    /**
     * Genera la plantilla HTML para el correo de invitar a un amigo
     */
    public static String plantillaInvitarAmigo(String correoAmigo, String nombreRemitente) {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        String fechaFormateada = ahora.format(formatter);
        String nombreInvitado = correoAmigo.split("@")[0];

        return """
        <!DOCTYPE html>
        <html>
        <body style="font-family: Arial, sans-serif; background-color: #f5f7fa; padding: 20px; margin: 0;">
            <table width="100%%" align="center" style="max-width: 500px; background: white; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.08);">
                <tr>
                    <td style="padding: 25px 30px 20px; border-bottom: 1px solid #eaeaea;">
                        <div style="text-align: center;">
                            <h1 style="margin: 0; color: #2c3e50; font-size: 30px; font-weight: 600;">
                                <span style="color: #4d4d4d;">Fernan</span><span style="color: #4d4d4d;">Events</span>
                            </h1>
                            <p style="margin: 5px 0 0; color: #7f8c8d; font-size: 13px;">¡Tienes una invitación especial!</p>
                        </div>
                    </td>
                </tr>
        
                <tr>
                    <td style="padding: 30px;">
                        <p style="margin: 0 0 15px; color: #333; font-size: 18px; text-align: center;">
                            ¡Hola <strong>%s</strong>!
                        </p>
        
                        <div style="text-align: center; padding: 10px 0;">
                            <span style="font-size: 50px;">✉️</span>
                        </div>
        
                        <p style="margin: 20px 0; color: #555; font-size: 16px; line-height: 1.6; text-align: center;">
                            Tu amigo/a <strong style="color: #2c3e50;">%s</strong> te ha invitado a formar parte de <strong>FernanEvents</strong>.
                        </p>
        
                        <div style="background: #fdf2f2; border-radius: 6px; padding: 20px; text-align: center; margin: 25px 0; border: 1px dashed #e2a6d9;">
                            <p style="margin: 0; color: #2c3e50; font-size: 15px; font-style: italic;">
                                "¡Ponte en contacto con tu amigo/a para más detalles. Te estaremos esperando!"
                            </p>
                        </div>
        
                        <div style="margin-top: 25px; padding-top: 20px; border-top: 1px solid #eee; text-align: center;">
                            <p style="margin: 0; font-size: 12px; color: #95a5a6;">
                                Invitación enviada el %s
                            </p>
                        </div>
                    </td>
                </tr>
        
                <tr>
                    <td style="padding: 20px 30px; background: linear-gradient(135deg, #9fd7ff, #e2a6d9); border-radius: 0 0 8px 8px;">
                        <p style="margin: 0; text-align: center; font-size: 12px; color: #2c3e50;">
                            © 2026 FernanEvents • ¡Únete a la comunidad!
                        </p>
                    </td>
                </tr>
            </table>
        </body>
        </html>
        """.formatted(nombreInvitado, nombreRemitente, fechaFormateada);
    }

    /**
     * Genera un Excel con el resumen de eventos de un organizador y lo envía por correo
     */
    public static void enviarResumenEventosOrganizador(String correoOrganizador, String nombreOrganizador, List<FernanEvents.modelo.Evento> eventos) {
        try {
            // Crear Excel
            Workbook wb = new XSSFWorkbook();
            Sheet hoja = wb.createSheet("Mis Eventos");

            // Cabecera
            Row cabecera = hoja.createRow(0);
            String[] columnas = {"Nombre", "Categoría", "Fecha", "Aforo", "Inscritos", "Aforo restante", "Tipo entrada", "Precio", "Disponibles"};
            for (int i = 0; i < columnas.length; i++) {
                cabecera.createCell(i).setCellValue(columnas[i]);
            }

            int fila = 1;
            for (FernanEvents.modelo.Evento e : eventos) {
                if (e.getOrganizador() != null && e.getOrganizador().getCorreo().equalsIgnoreCase(correoOrganizador)) {
                    if (e.getTiposDeEntrada().isEmpty()) {
                        Row row = hoja.createRow(fila++);
                        row.createCell(0).setCellValue(e.getNombre());
                        row.createCell(1).setCellValue(e.getCategoria().toString());
                        row.createCell(2).setCellValue(e.getFecha().toString());
                        row.createCell(3).setCellValue(e.getAforo());
                        row.createCell(4).setCellValue(e.getPersonasInscritas());
                        row.createCell(5).setCellValue(e.getAforoRestante());
                    } else {
                        for (FernanEvents.modelo.Entrada entrada : e.getTiposDeEntrada()) {
                            Row row = hoja.createRow(fila++);
                            row.createCell(0).setCellValue(e.getNombre());
                            row.createCell(1).setCellValue(e.getCategoria().toString());
                            row.createCell(2).setCellValue(e.getFecha().toString());
                            row.createCell(3).setCellValue(e.getAforo());
                            row.createCell(4).setCellValue(e.getPersonasInscritas());
                            row.createCell(5).setCellValue(e.getAforoRestante());
                            row.createCell(6).setCellValue(entrada.getCategoria().toString());
                            row.createCell(7).setCellValue(entrada.getPrecio());
                            row.createCell(8).setCellValue(entrada.getCantidadDisponible());
                        }
                    }
                }
            }

            // Guardar archivo temporal
            File archivo = File.createTempFile("eventos_" + nombreOrganizador, ".xlsx");
            try (FileOutputStream fos = new FileOutputStream(archivo)) {
                wb.write(fos);
            }
            wb.close();

            // Enviar correo con adjunto
            String remitente = "chemamc3@gmail.com";
            String clave = "blne cdaw znbi vmtn";
            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.user", remitente);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoOrganizador));
            message.setSubject("FernanEvents - Resumen de tus eventos");

            MimeBodyPart textoParte = new MimeBodyPart();
            textoParte.setContent(plantillaResumenEventos(nombreOrganizador), "text/html; charset=utf-8");

            MimeBodyPart adjuntoParte = new MimeBodyPart();
            DataSource source = new FileDataSource(archivo);
            adjuntoParte.setDataHandler(new javax.activation.DataHandler(source));
            adjuntoParte.setFileName("resumen_eventos_" + nombreOrganizador + ".xlsx");

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textoParte);
            multipart.addBodyPart(adjuntoParte);
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            archivo.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera un Excel con el resumen de entradas de un asistente y lo envía por correo
     */
    public static void enviarResumenEntradasAsistente(String correoAsistente, String nombreAsistente, Map<String, Integer> eventosInscrito, List<FernanEvents.modelo.Evento> eventos) {
        try {
            Workbook wb = new XSSFWorkbook();
            Sheet hoja = wb.createSheet("Mis Entradas");

            Row cabecera = hoja.createRow(0);
            String[] columnas = {"Evento", "Categoría", "Fecha", "Nº Entradas"};
            for (int i = 0; i < columnas.length; i++) {
                cabecera.createCell(i).setCellValue(columnas[i]);
            }

            int fila = 1;
            for (Map.Entry<String, Integer> entry : eventosInscrito.entrySet()) {
                String nombreEvento = entry.getKey();
                int cantidad = entry.getValue();
                Row row = hoja.createRow(fila++);
                row.createCell(0).setCellValue(nombreEvento);

                // Buscar datos del evento para añadir categoría y fecha
                FernanEvents.modelo.Evento evento = null;
                for (FernanEvents.modelo.Evento e : eventos) {
                    if (e.getNombre().equalsIgnoreCase(nombreEvento)) {
                        evento = e;
                        break;
                    }
                }
                if (evento != null) {
                    row.createCell(1).setCellValue(evento.getCategoria().toString());
                    row.createCell(2).setCellValue(evento.getFecha().toString());
                } else {
                    row.createCell(1).setCellValue("Evento eliminado");
                    row.createCell(2).setCellValue("-");
                }
                row.createCell(3).setCellValue(cantidad);
            }

            File archivo = File.createTempFile("entradas_" + nombreAsistente, ".xlsx");
            try (FileOutputStream fos = new FileOutputStream(archivo)) {
                wb.write(fos);
            }
            wb.close();

            String remitente = "chemamc3@gmail.com";
            String clave = "blne cdaw znbi vmtn";
            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.user", remitente);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoAsistente));
            message.setSubject("FernanEvents - Resumen de tus entradas");

            MimeBodyPart textoParte = new MimeBodyPart();
            textoParte.setContent(plantillaResumenEntradas(nombreAsistente), "text/html; charset=utf-8");

            MimeBodyPart adjuntoParte = new MimeBodyPart();
            DataSource source = new FileDataSource(archivo);
            adjuntoParte.setDataHandler(new javax.activation.DataHandler(source));
            adjuntoParte.setFileName("resumen_entradas_" + nombreAsistente + ".xlsx");

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textoParte);
            multipart.addBodyPart(adjuntoParte);
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            archivo.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Plantilla HTML del correo de resumen de eventos para organizadores
     */
    public static String plantillaResumenEventos(String nombreOrganizador) {
        return """
    <!DOCTYPE html>
    <html>
    <body style="font-family: Arial, sans-serif; background-color: #f5f7fa; padding: 20px; margin: 0;">
        <table width="100%%" align="center" style="max-width: 500px; background: white; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.08);">
            <tr>
                <td style="padding: 25px 30px 20px; border-bottom: 1px solid #eaeaea;">
                    <div style="text-align: center;">
                        <h1 style="margin: 0; color: #2c3e50; font-size: 30px; font-weight: 600;">
                            <span style="color: #4d4d4d;">Fernan</span><span style="color: #4d4d4d;">Events</span>
                        </h1>
                        <p style="margin: 5px 0 0; color: #7f8c8d; font-size: 13px;">Resumen de tus eventos</p>
                    </div>
                </td>
            </tr>
            <tr>
                <td style="padding: 30px;">
                    <p style="margin: 0 0 15px; color: #333; font-size: 15px;">
                        ¡Hola <strong style="color: #2c3e50;">%s</strong>!
                    </p>
                    <p style="margin: 0 0 20px; color: #555; font-size: 15px; line-height: 1.5;">
                        Adjunto encontrarás un Excel con el resumen de todos tus eventos en FernanEvents.
                    </p>
                </td>
            </tr>
            <tr>
                <td style="padding: 20px 30px; background: linear-gradient(135deg, #9fd7ff, #e2a6d9); border-radius: 0 0 8px 8px;">
                    <p style="margin: 0; text-align: center; font-size: 12px; color: #2c3e50;">
                        © 2026 FernanEvents • Correo automático
                    </p>
                </td>
            </tr>
        </table>
    </body>
    </html>
    """.formatted(nombreOrganizador);
    }

    /**
     * Plantilla HTML del correo de resumen de entradas para asistentes
     */
    public static String plantillaResumenEntradas(String nombreAsistente) {
        return """
    <!DOCTYPE html>
    <html>
    <body style="font-family: Arial, sans-serif; background-color: #f5f7fa; padding: 20px; margin: 0;">
        <table width="100%%" align="center" style="max-width: 500px; background: white; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.08);">
            <tr>
                <td style="padding: 25px 30px 20px; border-bottom: 1px solid #eaeaea;">
                    <div style="text-align: center;">
                        <h1 style="margin: 0; color: #2c3e50; font-size: 30px; font-weight: 600;">
                            <span style="color: #4d4d4d;">Fernan</span><span style="color: #4d4d4d;">Events</span>
                        </h1>
                        <p style="margin: 5px 0 0; color: #7f8c8d; font-size: 13px;">Resumen de tus entradas</p>
                    </div>
                </td>
            </tr>
            <tr>
                <td style="padding: 30px;">
                    <p style="margin: 0 0 15px; color: #333; font-size: 15px;">
                        ¡Hola <strong style="color: #2c3e50;">%s</strong>!
                    </p>
                    <p style="margin: 0 0 20px; color: #555; font-size: 15px; line-height: 1.5;">
                        Adjunto encontrarás un Excel con el resumen de todas tus entradas en FernanEvents.
                    </p>
                </td>
            </tr>
            <tr>
                <td style="padding: 20px 30px; background: linear-gradient(135deg, #9fd7ff, #e2a6d9); border-radius: 0 0 8px 8px;">
                    <p style="margin: 0; text-align: center; font-size: 12px; color: #2c3e50;">
                        © 2026 FernanEvents • Correo automático
                    </p>
                </td>
            </tr>
        </table>
    </body>
    </html>
    """.formatted(nombreAsistente);
    }
}
