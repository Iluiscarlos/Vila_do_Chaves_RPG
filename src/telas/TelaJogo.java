package telas;

import ferramentas.CaixaDeDialogo;
import ferramentas.Globais;
import java.awt.Image;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import modelo.Oponente;
import modelo.Personagem;

/**
 *
 * @author luisk
 */
public class TelaJogo extends javax.swing.JFrame {

    //protected static ArrayList<Personagem>arrPersonagem;
    Personagem personagem;
    Oponente inimigo = new Oponente();
    int maxVidaPersonagem;
    int maxVidaOponente;

    public TelaJogo() {
        initComponents();
        
        

    }

    public TelaJogo(Personagem personagem) {
        initComponents();
        ajustarImagem("/imagens/vila.jpg", lblVila);
        if (personagem.getNome().equals("Chaves")) {
            ajustarImagem("/imagens/chaves.png", lblEscolhido);
        } else if (personagem.getNome().equals("Kiko")) {
            ajustarImagem("/imagens/kiko.png", lblEscolhido);
        } else if (personagem.getNome().equals("Chiquinha")) {
            ajustarImagem("/imagens/chiquinha.png", lblEscolhido);
        } else if (personagem.getNome().equals("Seu Madruga")) {
            ajustarImagem("/imagens/seu_madruga.png", lblEscolhido);
        } else if (personagem.getNome().equals("Dona Florinda")) {
            ajustarImagem("/imagens/dona_florinda.png", lblEscolhido);
        } else if (personagem.getNome().equals("Bruxa do 71")) {
            ajustarImagem("/imagens/dona_clotilde.png", lblEscolhido);
        }
        this.personagem = personagem;
        this.maxVidaPersonagem = personagem.getVida();
        this.maxVidaOponente = inimigo.getVida();
        btnJogarNovamente.setEnabled(false);

        //Preenche os dados do personagem
        mostrarPersonagem();
        gerarOponente(); //Gerando um oponente com ataque e vida
        mostrarOponente();
    }

    private void mostrarPersonagem() {
        prbVidaPersonagem.setMaximum(maxVidaPersonagem);
        prbVidaPersonagem.setValue(personagem.getVida());

        lblCasa.setText(String.valueOf(personagem.getCasa()));
        lblPersonagem.setText(String.valueOf(personagem.getNome()));
        lblVidaPersonagem.setText(String.valueOf(personagem.getVida()));
        lblAtaquePersonagem.setText(String.valueOf(personagem.getAtaque()));

    }

    private void gerarOponente() {
        try {
               
          //FALTOU A VALIDAÇÃO, PARA NÃO SER OS MESMOS PERSONAGENS. FICA PARA A PROXIMA!
            
                int sorteado = Globais.gerarNumero(6);
            
            if (sorteado == 1 ) {
              
                ajustarImagem("/imagens/seu_madruga.png", lblInimigo);
                inimigo = new Oponente();
                inimigo.setCasa("72");
                inimigo.setNome("Seu Madruga");
                inimigo.setVida(80);
                inimigo.setAtaque(15);
            } else if (sorteado == 2) {
              
                ajustarImagem("/imagens/chiquinha.png", lblInimigo);
                inimigo = new Oponente();
                inimigo.setCasa("72");
                inimigo.setNome("Chiquinha");
                inimigo.setVida(90);
                inimigo.setAtaque(25);
            
            } else if (sorteado == 3) {
             //   adEscolhido.setNome("Chaves");
                ajustarImagem("/imagens/chaves.png", lblInimigo);
                inimigo = new Oponente();
                inimigo.setCasa("8");
                inimigo.setNome("Chaves");
                inimigo.setVida(70);
                inimigo.setAtaque(30);
            
            } else if (sorteado == 4) {
             
                ajustarImagem("/imagens/kiko.png", lblInimigo);
                inimigo = new Oponente();
                inimigo.setCasa("14");
                inimigo.setNome("Kiko");
                inimigo.setVida(95);
                inimigo.setAtaque(20);
            
            } else if (sorteado == 5) {
             
                  ajustarImagem("/imagens/dona_florinda.png", lblInimigo);
                inimigo = new Oponente();
                inimigo.setCasa("14");
                inimigo.setNome("Dona Florinda");
                inimigo.setVida(95);
                inimigo.setAtaque(35);
            
            } else if (sorteado == 6) {
            
                ajustarImagem("/imagens/dona_clotilde.png", lblInimigo);
                inimigo = new Oponente();
                inimigo.setCasa("71");
                inimigo.setNome("Bruxa do 71");
                inimigo.setVida(70);
                inimigo.setAtaque(32);
            
            }
            maxVidaOponente = inimigo.getVida();
            
        } catch (Exception ex) {
            CaixaDeDialogo.obterinstancia().exibirMensagem(ex.getMessage().toString(), 'e');
        }
    }

    private void mostrarOponente() {
        prbVidaOponente.setMaximum(maxVidaOponente);
        prbVidaOponente.setValue(inimigo.getVida());

        lblCasaAd.setText(inimigo.getCasa());
        lblNomeAd.setText(inimigo.getNome());
        lblVidaAd.setText(String.valueOf(inimigo.getVida()));
        lblAtaqueAd.setText(String.valueOf(inimigo.getAtaque()));
    }

    private void ataque() {
        //personagem ataca o oponente
       
        int numero = Globais.gerarNumero(2);
       
        if(numero == 1){
            prbVidaOponente.setValue(prbVidaOponente.getValue() - Globais.gerarNumero(personagem.getAtaque()) );
            lblVidaAd.setText(String.valueOf(prbVidaOponente.getValue()));
            lblPainel.setText("Você atacou "+inimigo.getNome()+"!" + " +5 Vida");
            
            lblVidaPersonagem.setText(String.valueOf(prbVidaPersonagem.getValue() + 5));
            prbVidaPersonagem.setValue(prbVidaPersonagem.getValue() + 5);
        }else if (numero == 2) {
            prbVidaPersonagem.setValue(prbVidaPersonagem.getValue() - Globais.gerarNumero(inimigo.getAtaque()));
            lblVidaPersonagem.setText(String.valueOf(prbVidaPersonagem.getValue()));
            lblPainel.setText(inimigo.getNome()+" atacou você!");
           
            lblVidaAd.setText(String.valueOf(prbVidaOponente.getValue() + 5));
            prbVidaOponente.setValue(prbVidaOponente.getValue() + 5 );
            
            
        }
        if (prbVidaOponente.getValue() == 0){
            Globais.tocarSom("/src/song/venceu.wav");
            Globais.exibirMensagem("E zas!!!\n"
                    + "Você derrotou " + inimigo.getNome()+"!");
            btnAtacar.setEnabled(false);
        }else if(prbVidaPersonagem.getValue() == 0){
            Globais.tocarSom("/src/song/perdeu.wav");
            Globais.exibirMensagem("Game Over!!\n"
                    + inimigo.getNome() + " derrotou você!");
            btnAtacar.setEnabled(false);
            
        }
        
    }

    private void ajustarImagem(String caminho, JLabel label) {
        try {

            ImageIcon imageIcon = new ImageIcon(getClass().getResource(caminho));
            Image img = imageIcon.getImage();
            Image imgScale = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledimageIcon = new ImageIcon(imgScale);
            label.setIcon(scaledimageIcon);

        } catch (Exception ex) {

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnAtacar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        prbVidaPersonagem = new javax.swing.JProgressBar();
        lblCasa = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblPersonagem = new javax.swing.JLabel();
        lblVidaPersonagem = new javax.swing.JLabel();
        lblAtaquePersonagem = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblCasaAd = new javax.swing.JLabel();
        lblNomeAd = new javax.swing.JLabel();
        lblVidaAd = new javax.swing.JLabel();
        lblAtaqueAd = new javax.swing.JLabel();
        prbVidaOponente = new javax.swing.JProgressBar();
        btnJogarNovamente = new javax.swing.JButton();
        lblPainel = new javax.swing.JTextField();
        lblInimigo = new javax.swing.JLabel();
        lblEscolhido = new javax.swing.JLabel();
        lblVila = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("V.S");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, -1));

        btnAtacar.setText("Atacar");
        btnAtacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtacarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAtacar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        prbVidaPersonagem.setString("%");
        jPanel1.add(prbVidaPersonagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 160, 20));

        lblCasa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCasa.setText("......");
        jPanel1.add(lblCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 50, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Casa:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nome:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Vida:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Ataque:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        lblPersonagem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPersonagem.setText("......");
        jPanel1.add(lblPersonagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 90, -1));

        lblVidaPersonagem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblVidaPersonagem.setText("......");
        jPanel1.add(lblVidaPersonagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 50, -1));

        lblAtaquePersonagem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAtaquePersonagem.setText("......");
        jPanel1.add(lblAtaquePersonagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 50, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 190, 130));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Casa:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Nome:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Vida:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Ataque:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        lblCasaAd.setText("......");
        jPanel2.add(lblCasaAd, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        lblNomeAd.setText("......");
        jPanel2.add(lblNomeAd, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 80, -1));

        lblVidaAd.setText("......");
        jPanel2.add(lblVidaAd, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        lblAtaqueAd.setText("......");
        jPanel2.add(lblAtaqueAd, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        prbVidaOponente.setString("%");
        jPanel2.add(prbVidaOponente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 200, 130));

        btnJogarNovamente.setText("Jogar Novamente!");
        btnJogarNovamente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJogarNovamenteActionPerformed(evt);
            }
        });
        getContentPane().add(btnJogarNovamente, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, -1, -1));

        lblPainel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblPainel.setText("...");
        getContentPane().add(lblPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 620, -1));

        lblInimigo.setText("Inimigo");
        lblInimigo.setMaximumSize(new java.awt.Dimension(51, 16));
        lblInimigo.setMinimumSize(new java.awt.Dimension(51, 16));
        getContentPane().add(lblInimigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, 170, 210));

        lblEscolhido.setText("Escolhido");
        getContentPane().add(lblEscolhido, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 170, 210));

        lblVila.setText("Vila");
        getContentPane().add(lblVila, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 610, 340));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtacarActionPerformed
        ataque();
        Globais.tocarSom("/src/song/pancada.wav");
        
        if(!btnAtacar.isEnabled()){
            btnJogarNovamente.setEnabled(true);
        }
    }//GEN-LAST:event_btnAtacarActionPerformed

    private void btnJogarNovamenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJogarNovamenteActionPerformed
        
        new TelaInicial().setVisible(true);
    
        this.dispose();
    }//GEN-LAST:event_btnJogarNovamenteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaJogo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtacar;
    private javax.swing.JButton btnJogarNovamente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAtaqueAd;
    private javax.swing.JLabel lblAtaquePersonagem;
    private javax.swing.JLabel lblCasa;
    private javax.swing.JLabel lblCasaAd;
    private javax.swing.JLabel lblEscolhido;
    private javax.swing.JLabel lblInimigo;
    private javax.swing.JLabel lblNomeAd;
    private javax.swing.JTextField lblPainel;
    private javax.swing.JLabel lblPersonagem;
    private javax.swing.JLabel lblVidaAd;
    private javax.swing.JLabel lblVidaPersonagem;
    private javax.swing.JLabel lblVila;
    private javax.swing.JProgressBar prbVidaOponente;
    private javax.swing.JProgressBar prbVidaPersonagem;
    // End of variables declaration//GEN-END:variables
}
