import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
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

import javax.swing.BoxLayout;



public class Server extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(final String rmessage) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server(rmessage);
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
	public Server(String rmessage) {
		System.out.println("rmessage"+ rmessage);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,150,200,150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JTextArea textArea = new JTextArea();
		textArea.append(rmessage);
		contentPane.add(textArea);
		
		setTitle("Server Machine");
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		setPreferredSize(new Dimension(450, 110));
		add(scrollPane, BorderLayout.CENTER);
	}

}
