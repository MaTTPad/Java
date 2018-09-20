import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author mpadula
 */
public class loginWindow extends javax.swing.JFrame {

    /**
     * Creates new form loginWindow
     */
    public loginWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        loginField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        wrongNameLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        wrongPasswordLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        closeItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(5, 2));

        jLabel1.setText("Login:");
        jPanel1.add(jLabel1);

        loginField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                loginFieldFocusLost(evt);
            }
        });
        jPanel1.add(loginField);
        jPanel1.add(jLabel2);

        wrongNameLabel.setForeground(new java.awt.Color(255, 51, 0));
        jPanel1.add(wrongNameLabel);

        jLabel4.setText("Hasło:");
        jPanel1.add(jLabel4);

        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFieldFocusLost(evt);
            }
        });
        jPanel1.add(passwordField);
        jPanel1.add(jLabel6);

        wrongPasswordLabel.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(wrongPasswordLabel);

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        jPanel1.add(loginButton);

        jMenu1.setText("Program");

        closeItem.setText("Zamknij");
        closeItem.setActionCommand("Zamknij");
        closeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeItemActionPerformed(evt);
            }
        });
        jMenu1.add(closeItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_closeItemActionPerformed

    public boolean isLoginCorrect()
    {
        String login=loginField.getText();
        int length=login.length();
        if(length<3 || length>20)
        {
            return false;
        }
        else return true;
    }
    
    public boolean isPasswordCorrect()
    {
        String password=passwordField.getText();
        int length=password.length();
        if(length<3 || length>20)
        {
            return false;
        }
        else return true;
    }
    
    private void loginFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginFieldFocusLost
        if(isLoginCorrect())
           wrongNameLabel.setText("");
        else
           wrongNameLabel.setText("Niepoprawna długość loginu.");

    }//GEN-LAST:event_loginFieldFocusLost

    private void passwordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusLost
if(isPasswordCorrect())
           wrongPasswordLabel.setText("");
        else
           wrongPasswordLabel.setText("Niepoprawna długość hasła.");
    }//GEN-LAST:event_passwordFieldFocusLost

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        if(loginField.getText().equals("mpadula") && passwordField.getText().equals("12345"))
        {
            usersWindow newWindow=new usersWindow();
            newWindow.openWindow();
        }
        else
        JOptionPane.showMessageDialog(new JFrame(), "Niepoprawny login lub hasło.", "Error.",
        JOptionPane.ERROR_MESSAGE);

    }//GEN-LAST:event_loginButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="colg.JPasswordField passwordField;
    private javax.swing.JLabel wrongNameLabel;
    private javax.swing.JLabel wrongPasswordLabel;
    // End of variables declaration//GEN-END:variables
}
