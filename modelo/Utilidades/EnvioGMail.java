package FernanEvents.modelo.Utilidades;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;

public class EnvioGMail {
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
                            © 2025 FernanEvents • Correo automático de registro
                        </p>
                    </td>
                </tr>
            </table>
        </body>
        </html>
        """.formatted(nombreUsuario, codigoToken, nombreUsuario, fechaHoraFormateada);
        }

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
                                    © 2025 FernanEvents • Correo automático de seguridad
                                </p>
                            </td>
                        </tr>
                    </table>
                </body>
                </html>
                """.formatted(usuario, codigo, fechaHoraFormateada);
        }

        public static void main(String[] args) {
        }
    }
