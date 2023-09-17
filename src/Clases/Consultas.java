package Clases;

    import ConexionConMySQL.Conexion;
    import Formularios.Crud;
import java.awt.HeadlessException;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.Statement;
    import java.sql.SQLException;
    import javax.swing.JOptionPane;

public class Consultas {
    public void GuardarUsuario(String nombre, String apellido, String usuario, String email, String contraseña){
        Conexion db = new Conexion();
        String sql = "insert into usuarios(Nombre,Apellido,Usuario,Email,Contraseña) values('" + nombre + "','" + apellido + "','" + usuario + "','" + email + "','" + contraseña + "')";
        Statement st;
        Connection conexion = db.getConnection();
        try{
            st = conexion.createStatement();
            int rs = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Guardado correctamente");    
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void AccesoUsuario(String User, String Password){
        Conexion db = new Conexion();
        String UsuarioCorrecto = null;
        String PaswordCorrecto = null;
        
        try{
            Connection cn = db.getConnection();
            PreparedStatement pst = cn.prepareStatement("SELECT Usuario, Contraseña FROM usuarios");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                UsuarioCorrecto = rs.getString(1);
                PaswordCorrecto = rs.getString(2); 
            }
            
            if(User.equals(UsuarioCorrecto) && Password.equals(PaswordCorrecto)){
                JOptionPane.showMessageDialog(null, "Bienvenido " + User);
                Crud f1 = new Crud();
                f1.setVisible(true);
            }else if(!User.equals(UsuarioCorrecto) && Password.equals(PaswordCorrecto)){
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR " + e);
        }
    }
}
