package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import DAO.UserRepo;
import model.User;
import table.TableUser;

import javax.swing.JButton;
import java.util.List;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserFrame {

    private JFrame frame;
    private JTextField txtName;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTable tableUsers;

    UserRepo usr = new UserRepo();
    List<User> ls;
    public String id;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserFrame window = new UserFrame();
                    window.frame.setVisible(true);
                    window.loadTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void reset() {
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
    }

    public void loadTable() {
        ls = usr.show();
        TableUser tu = new TableUser(ls);
        tableUsers.setModel(tu);
        tableUsers.getTableHeader().setVisible(true);
    }

    /**
     * Create the application.
     */
    public UserFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 797, 891);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("NAMA");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(50, 67, 84, 14);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("USERNAME");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(50, 128, 84, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("PASSWORD");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(50, 188, 84, 14);
        frame.getContentPane().add(lblNewLabel_2);

        txtName = new JTextField();
        txtName.setBounds(144, 58, 527, 37);
        frame.getContentPane().add(txtName);
        txtName.setColumns(10);

        txtUsername = new JTextField();
        txtUsername.setColumns(10);
        txtUsername.setBounds(144, 119, 527, 37);
        frame.getContentPane().add(txtUsername);

        txtPassword = new JTextField();
        txtPassword.setColumns(10);
        txtPassword.setBounds(144, 179, 527, 37);
        frame.getContentPane().add(txtPassword);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setNama(txtName.getText());
                user.setUsername(txtUsername.getText());
                user.setPassword(txtPassword.getText());
                usr.save(user);
                reset();
                loadTable(); // refresh table setelah simpan
            }
        });
        btnSave.setBackground(new Color(0, 128, 0));
        btnSave.setBounds(50, 257, 131, 48);
        frame.getContentPane().add(btnSave);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setNama(txtName.getText());
				user.setUsername(txtUsername.getText());
				user.setPassword(txtPassword.getText());
				user.setId(id);
				usr.update(user);
				reset();
				loadTable();
			}
		});
		
        btnUpdate.setBackground(new Color(0, 0, 255));
        btnUpdate.setBounds(219, 257, 131, 48);
        frame.getContentPane().add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (id != null)	{
        			usr.delete(id);
        			reset();
        			loadTable();
        		} else {
        			JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan di hapus bang");
        		}
        	}
        });
        btnDelete.setBackground(new Color(165, 42, 42));
        btnDelete.setBounds(393, 257, 131, 48);
        frame.getContentPane().add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        btnCancel.setBackground(new Color(0, 0, 0));
        btnCancel.setBounds(563, 257, 131, 48);
        frame.getContentPane().add(btnCancel);

        tableUsers = new JTable();
        tableUsers.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		id = tableUsers.getValueAt(tableUsers.getSelectedRow(),0).toString();
        		txtName.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(),1).toString());
        		txtUsername.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(),2).toString());
        		txtPassword.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(),3).toString());
        		
        	}
        });
        JScrollPane scrollPane = new JScrollPane(tableUsers);
        scrollPane.setBounds(50, 344, 644, 429);
        frame.getContentPane().add(scrollPane);
    }
}
