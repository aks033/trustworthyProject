import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Client extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea txtrClientMessages;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		setTitle("Client Machine");
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{String text= textField.getText();
				final String newline = "\n";
				System.out.println(text);
				textField.setText("");
				txtrClientMessages.append(text + newline);

				ClientOnClass.main(text);
				}catch(Exception e){
					e.printStackTrace();
				}		
			}
		});
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSend, -31, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnSend, -10, SpringLayout.EAST, contentPane);
		contentPane.add(btnSend);
		
		textField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, -11, SpringLayout.NORTH, btnSend);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 29, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField, 45, SpringLayout.NORTH, btnSend);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField, -15, SpringLayout.WEST, btnSend);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTypeYourMessage = new JLabel("Type your message here:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblTypeYourMessage, 0, SpringLayout.WEST, textField);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblTypeYourMessage, -6, SpringLayout.NORTH, textField);
		contentPane.add(lblTypeYourMessage);
		
		txtrClientMessages = new JTextArea();
		txtrClientMessages.setTabSize(0);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtrClientMessages, 6, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtrClientMessages, 0, SpringLayout.WEST, textField);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtrClientMessages, -6, SpringLayout.NORTH, lblTypeYourMessage);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtrClientMessages, -15, SpringLayout.EAST, contentPane);
		contentPane.add(txtrClientMessages);
		
		
		txtrClientMessages.setLineWrap(true);
		txtrClientMessages.setEditable(false);
		
		 JScrollPane scroll = new JScrollPane (txtrClientMessages);
		 sl_contentPane.putConstraint(SpringLayout.NORTH, scroll, 27, SpringLayout.NORTH, contentPane);
		 sl_contentPane.putConstraint(SpringLayout.WEST, scroll, 29, SpringLayout.WEST, contentPane);
		 sl_contentPane.putConstraint(SpringLayout.SOUTH, scroll, 0, SpringLayout.NORTH, lblTypeYourMessage);
		 sl_contentPane.putConstraint(SpringLayout.EAST, scroll, -21, SpringLayout.EAST, contentPane);
		 scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 contentPane.add(scroll);
	}

}
