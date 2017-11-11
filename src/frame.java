import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class frame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	static java.sql.Connection conn = null;


	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		String url = ("jdbc:sqlite:/Users/Darshana/Desktop/sqlite/test.db");
		//jdbc:sqlite:/Users/PranathiVasireddy/Desktop/sqlite/test.db
		conn = DriverManager.getConnection(url);
		System.out.println("Connection Established");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame frame = new frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public frame() throws SQLException {
		final Statement stmt = conn.createStatement();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblTextme = new JLabel("TextME");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblTextme, 174, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblTextme, -442, SpringLayout.SOUTH, contentPane);
		contentPane.add(lblTextme);
		
		JLabel lblNewLabel = new JLabel("New label");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 6, SpringLayout.SOUTH, lblTextme);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 61, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, -131, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, -85, SpringLayout.EAST, contentPane);
		lblNewLabel.setIcon(new ImageIcon("/Users/PranathiVasireddy/Desktop/unnamed.png"));
		contentPane.add(lblNewLabel);
		
		JButton btnNewUser = new JButton("New User");
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewUser, -21, SpringLayout.EAST, contentPane);
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				String username = "";
				String password = "";
				
				username = textField.getText().trim();
				password = String.valueOf(passwordField.getPassword());
				if (username.equals("") || password.equals(""))
				{
					JOptionPane.showMessageDialog(null," Please enter valid credentials!","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {					
					try {
					String sql = ("SELECT USERNAME FROM LOGIN WHERE USERNAME ='"+username+"'");
					ResultSet rs = stmt.executeQuery(sql);
					if (rs.next())
					{
						JOptionPane.showMessageDialog(null," Username already exists!","Error",JOptionPane.ERROR_MESSAGE);	
					}
					else {    
				    String seq = ("INSERT INTO LOGIN (USERNAME,PASSWORD) " +
				                       "VALUES('"+username+"','"+password+"')"); 
				    stmt.execute(seq); 
				    System.out.println("Created");

					setVisible(false);
					new Client().setVisible(true);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						try {
							if (conn != null) {
								conn.close();
							}
						}  catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					}	
				}
			}
	});
		contentPane.add(btnNewUser);
		
		JButton btnLogin = new JButton("Login");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewUser, -14, SpringLayout.NORTH, btnLogin);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnLogin, -42, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnLogin, -28, SpringLayout.EAST, contentPane);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				String username = "";
				String password = "";
				
				username = textField.getText().trim();
				password = String.valueOf(passwordField.getPassword());
				
				if (username.equals("") || password.equals("")){
					JOptionPane.showMessageDialog(null," Please enter valid credentials!","Error",JOptionPane.ERROR_MESSAGE);
				}else{
					try {
				        String sql = ("SELECT USERNAME FROM LOGIN "
				        		+ "WHERE USERNAME='"+username+"' ");
				        ResultSet rs = stmt.executeQuery(sql); 
				        if (rs.next()){
				        	String seq = ("SELECT PASSWORD FROM LOGIN WHERE PASSWORD='"+password+"'");
				        	ResultSet res = stmt.executeQuery(seq);
				        	if (res.next()) {
							setVisible(false);
							new Client().setVisible(true);
				        	}
				        	else {
								JOptionPane.showMessageDialog(null,"Please enter the correct password!","Error",JOptionPane.ERROR_MESSAGE);	
				        	}
				        }
				        else {
							JOptionPane.showMessageDialog(null,"Please verify the username and password!","Error",JOptionPane.ERROR_MESSAGE);
				        }
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						try {
							if (conn != null) {
								conn.close();
							}
						}  catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					}
				}
			}
		});
		contentPane.add(btnLogin);
		
		JLabel lblUsername = new JLabel("Username:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblUsername, 0, SpringLayout.NORTH, btnNewUser);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblUsername, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblPassword, 0, SpringLayout.SOUTH, btnLogin);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, -1, SpringLayout.NORTH, btnNewUser);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		sl_contentPane.putConstraint(SpringLayout.WEST, passwordField, 25, SpringLayout.EAST, lblPassword);
		sl_contentPane.putConstraint(SpringLayout.EAST, passwordField, -50, SpringLayout.WEST, btnLogin);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, passwordField);
		sl_contentPane.putConstraint(SpringLayout.NORTH, passwordField, -6, SpringLayout.NORTH, lblPassword);
		contentPane.add(passwordField);
	}
}
