package Activity;

import javax.swing.JOptionPane;

public class registerForm extends javax.swing.JFrame {

    public registerForm() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        lblRegister = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lblConfirmPassword = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        btnRegister = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create Account");
        setResizable(false);

        headerPanel.setBackground(new java.awt.Color(255, 153, 153));

        lblRegister.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblRegister.setForeground(new java.awt.Color(255, 255, 255));
        lblRegister.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegister.setText("REGISTER");
        headerPanel.add(lblRegister);

        lblUsername.setText("Username:");

        lblPassword.setText("Password:");

        lblConfirmPassword.setText("Confirm Password:");

        btnRegister.setBackground(new java.awt.Color(46, 204, 113));
        btnRegister.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.setText("REGISTER");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnBack.setText("Back to Login");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(headerPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(50, 50, 50)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(lblConfirmPassword)
                    .add(lblPassword)
                    .add(lblUsername)
                    .add(txtUsername, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .add(txtPassword)
                    .add(txtConfirmPassword)
                    .add(layout.createSequentialGroup()
                        .add(btnRegister, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(btnBack)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(headerPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(30, 30, 30)
                .add(lblUsername)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtUsername, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(lblPassword)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtPassword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(lblConfirmPassword)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtConfirmPassword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(btnRegister, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnBack))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {
    String user = txtUsername.getText();
    String pass = new String(txtPassword.getPassword());
    String confirm = new String(txtConfirmPassword.getPassword());

    if (user.isEmpty() || pass.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Fields cannot be empty!");
        return;
    }

    if (!pass.equals(confirm)) {
        JOptionPane.showMessageDialog(this, "Passwords do not match!");
        return;
    }

    String sql = "INSERT INTO users(username, password) VALUES(?, ?)";

    try (java.sql.Connection conn = DatabaseConnection.connect();
         java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, user);
        pstmt.setString(2, pass); // Note: In real apps, use hashing!
        pstmt.executeUpdate();
        
        JOptionPane.showMessageDialog(this, "Account Created Successfully!");
        btnBackActionPerformed(null); // Go to login
        
    } catch (java.sql.SQLException e) {
        if (e.getMessage().contains("UNIQUE constraint")) {
            JOptionPane.showMessageDialog(this, "Username already exists!");
        } else {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }
}                                       

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {                                        
        this.dispose();
        new loginForm().setVisible(true);
    }                                       

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new registerForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRegister;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel lblConfirmPassword;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRegister;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}