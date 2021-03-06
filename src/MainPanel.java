import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPanel extends JPanel {
	punteggio s;
	
	public MainPanel(){                                                 //Creazione del pannello di gioco e del pannello punteggio
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS) );
		s = new punteggio();
		pannelloGioco p = new pannelloGioco();
		this.add(s);
		this.add(p);
	}
	
	public class punteggio extends JPanel {                             //Dimensioni del pannello punteggio
		
		int larghezzaP=1280,altezzaP=220;
		int PunteggioP1=0, PunteggioP2=0;
		int setP1[] = new int[3];                                   //Vettori che determinano il numero dei game a 3
		int setP2[] = new int[3];                                   //Vettori che determinano il numero dei game a 3
		
		int gameP1=0, gameP2=0;                                     //Iniziallizzare i game a 0
		
		public punteggio(){                                                       
                        this.setPreferredSize(new Dimension(larghezzaP,altezzaP));              //A differenza del setSize(), offre le dimensioni perfette
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK));            //Colore del bordo della sezione del Punteggio
			this.setBackground(Color.WHITE);                                        //Colore dello sfonde del pannello punteggio
		}
		
                
                @Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.setColor(Color.BLACK);                                                //Modifica colore, font, posizione della scritta PING PONG
			g.setFont(new Font("Times New Roman",Font.PLAIN,48));
			g.drawString("PING - PONG", 1280/2-120, 220/2-50);
			
			g.setColor(Color.BLACK);                                                 //Modifica colore, font, posizione della scritta Giocatore 1
			g.setFont(new Font("Times New Roman",Font.PLAIN,36));
			g.drawString("Giocatore 1", 30, 120);
			g.drawString("Giocatore 2", 1280-190, 120);                        //Giocatore2
			
			g.setColor(Color.BLACK);                                                 //Modifica del colore e della posizione delle celle laterali relative al punteggio
			g.fill3DRect(75, 150, 60, 50,true);
			g.fill3DRect(1280-80-60, 150, 60, 50,true);
			
			g.setColor(Color.BLACK);                                                 //Modifica del colore e della posizione delle celle laterali relative al punteggio
			g.drawRect(75, 150, 60, 50);
			g.drawRect(1280-80-60, 150, 60, 50);
			
			g.setColor(Color.WHITE);                                                 //Colore della scritta del punteggio della scritta laterale
			g.drawString(Integer.toString(PunteggioP1), 90, 185);
			g.drawString(Integer.toString(PunteggioP2), 1280-80-45, 185);     
			
			g.setColor(Color.BLACK);                                                 //Colore dei giocatori relativo ai Set e Game 
			g.setFont(new Font("Times New Roman",Font.PLAIN,30));
			g.drawString("Giocatore 1", 1280/2-250, 115);
			g.drawString("Giocatore 2", 1280/2-250, 175);
			
			for(int i=1;i<=3;i++){                                                   //Colore delle cele relative ai Set dei due giocatori
				g.setColor(Color.BLACK);
				g.fill3DRect(1280/2-170+70*i, 80, 70, 60,true);
				g.fill3DRect(1280/2-170+70*i, 140, 70, 60,true);
				g.setColor(Color.WHITE);                                         //Colore dei bordi delle celle relative ai set del giocatore
				g.draw3DRect(1280/2-170+70*i, 80, 70, 60,true);
				g.draw3DRect(1280/2-170+70*i, 140, 70, 60,true);
			}
			
			for(int i=1;i<=3;i++){
				g.setColor(Color.WHITE);                                         //Colore del punteggio dei Set e Game del giocatore uno e due
				g.setFont(new Font("Times New Roman",Font.PLAIN,46));
				g.drawString(Integer.toString(setP1[i-1]), 1280/2-170+70*i+15, 125);
				g.drawString(Integer.toString(setP2[i-1]), 1280/2-170+70*i+15, 185);
				
			}
			
			g.setColor(Color.BLACK);                                                //Colore delle celle relative ai Game del giocatore
			g.fill3DRect(1280/2-190+80*4, 80, 70, 60,true);
			g.fill3DRect(1280/2-190+80*4, 140, 70, 60,true);
			g.setColor(Color.WHITE);                                                //Colore dei bordi delle celle relative ai Gsme del giocatore
			g.draw3DRect(1280/2-190+80*4, 80, 70, 60,true);
			g.draw3DRect(1280/2-190+80*4, 140, 70, 60,true);
			
			g.setColor(Color.WHITE);                                                //Colore del punteggio dei giocatori nella cella del game
			g.setFont(new Font("Times New Roman",Font.PLAIN,46));
			g.drawString(Integer.toString(gameP1), 1280/2-190+70*4+55, 125);
			g.drawString(Integer.toString(gameP2), 1280/2-190+70*4+55, 185);
			
		}
        }
	
	public class pannelloGioco extends JPanel implements ActionListener,KeyListener{
		
		int larghezzaSchermo=1280,altezzaSchermo=500;
		
		int larghezzaBarra=15,altezzaBarra=100;
		int BarraAx=10;
                int BarraAy=altezzaSchermo/2 - altezzaBarra/2;
		int velocitaBarraA=10;
		
		int BarraBx=larghezzaSchermo-30;
                int BarraBy=altezzaSchermo/2 - altezzaBarra/2;
		int velocitaBarraB=10;
		
		int raggioPalla=20;
		int pallaX=larghezzaSchermo/2-raggioPalla/2,pallaY=altezzaSchermo/2-raggioPalla/2;
		double velocitaBarraX=10,velocitaBarraY=10;
                
		
		int set=0;
		
		boolean inizio=false;
		boolean vincitore=false;
		
		String giocatoreVincente;
		
		boolean collisioneA=false;
		boolean collisioneB=false;
		
		
		Timer t;
		
		public pannelloGioco(){
			t= new Timer(1,this);
			this.setPreferredSize(new Dimension(larghezzaSchermo,altezzaSchermo));   //A differenza del setSize(), offre le dimensioni perfette
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK));             //
			this.setBackground(Color.BLACK);                                         //Colore dello sfondo del campo da gioco
			this.addKeyListener(this);
			setFocusable(true);
			this.setFocusTraversalKeysEnabled(false);
			t.start();
			
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			g.setColor(Color.WHITE);                                                 //Colore della barra 
			g.fill3DRect(BarraAx, BarraAy, larghezzaBarra, altezzaBarra,true);
			g.fill3DRect(BarraBx, BarraBy, larghezzaBarra, altezzaBarra,true);
			
			g.setColor(Color.RED);                                                   //COlore della palla
			g.fillOval(pallaX, pallaY, raggioPalla, raggioPalla);
			
			if(!inizio){
				ChiStartaMatch(g);
			}
			
			if(vincitore){
				ChiVince(g);
				t.stop();
			}
		}
                
                
		@Override
		public void actionPerformed(ActionEvent e1) {                                  //Azioni di palla e barra
			if(inizio){
				pallaX += velocitaBarraX;
				pallaY += velocitaBarraY;
			}
				BarraAy += velocitaBarraA*5;
				BarraBy += velocitaBarraB*5;
				
				
				if(BarraAy<0 || BarraAy+altezzaBarra>altezzaSchermo){
					velocitaBarraA=0;
				}
				if(BarraBy<0 || BarraBy+altezzaBarra>altezzaSchermo){
					velocitaBarraB=0;
				}
			
				
				
				if(pallaY < 0 || pallaY+raggioPalla > altezzaSchermo){
					velocitaBarraY = -velocitaBarraY;
				}
				if(pallaX < 0 ){
					s.PunteggioP2++;
					pallaX=larghezzaSchermo/2-raggioPalla/2;
					pallaY=altezzaSchermo/2-raggioPalla/2;
					
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(pallaX+raggioPalla > larghezzaSchermo){
					s.PunteggioP1++;
					pallaX=larghezzaSchermo/2-raggioPalla/2;
					pallaY=altezzaSchermo/2-raggioPalla/2;
					
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
				if(s.PunteggioP1 == 10 && s.PunteggioP2 == 10){
						s.PunteggioP1=9;
						s.PunteggioP2=9;
				}
				
				if(s.PunteggioP1 == 11){
					s.setP1[set]=s.PunteggioP1;
					s.setP2[set]=s.PunteggioP2;
					s.gameP1++;
					s.PunteggioP1=0;
					s.PunteggioP2=0;
					set++;
				}
				
				if(s.PunteggioP2 == 11){
					
						s.setP1[set]=s.PunteggioP1;
						s.setP2[set]=s.PunteggioP2;
						s.gameP2++;
						s.PunteggioP1=0;
						s.PunteggioP2=0;
						set++;
				}
				
				
				if(set==3){
					vincitore=true;
					if(s.gameP1 > s.gameP2){
						giocatoreVincente="Player 1";
					}else{
						giocatoreVincente="Player 2";
					}
				}
				
				Rectangle barraA = new Rectangle(BarraAx,BarraAy,larghezzaBarra,altezzaBarra);
				Rectangle barraB = new Rectangle(BarraBx,BarraBy,larghezzaBarra,altezzaBarra);
				Rectangle palla = new Rectangle(pallaX,pallaY,raggioPalla,raggioPalla);
				
				if(!collisioneA){
				if(barraA.intersects(palla)){
					velocitaBarraX = -velocitaBarraX;
					collisioneA = true;
                                        }
				}
				
				if(!collisioneB){
				if(barraB.intersects(palla)){
					velocitaBarraX = -velocitaBarraX;
					collisioneB=true;
                                       	}
				}
				
				if(collisioneA){
					if(!palla.intersects(barraA)){
                                        collisioneA=false;
					}
				}
				
				if(collisioneB){
					if(!palla.intersects(barraB)){
                                        collisioneB=false;
                          		}
				}
				
			repaint();
			s.repaint();
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			int chiave = e.getKeyCode();
			
			if(chiave == KeyEvent.VK_SPACE){
				inizio=true;
			}
			
			if(chiave == KeyEvent.VK_ESCAPE){
                                System.exit(0);
			}
			
			if(chiave == KeyEvent.VK_UP){
				if(BarraBy > 0){
				velocitaBarraB = -2;
			}else
				velocitaBarraB =0;
			}
			
			if(chiave == KeyEvent.VK_DOWN){
					if(BarraBy+altezzaBarra < altezzaSchermo){
					velocitaBarraB = 2;
				}else
					velocitaBarraB = 0;
			}
			
				
			if(chiave == KeyEvent.VK_W){
				if(BarraAy > 0 ){
				velocitaBarraA = -2;
			}else
				velocitaBarraA =0;
			}
			
			
			if(chiave == KeyEvent.VK_S){
					if(BarraAy+altezzaBarra < altezzaSchermo){
					velocitaBarraA = 2;
			}else
					velocitaBarraA = 0;
			}	
		}
                
                
		@Override
		public void keyReleased(KeyEvent e) {
			int chiave = e.getKeyCode();
			if(chiave == KeyEvent.VK_UP || chiave == KeyEvent.VK_DOWN){
				velocitaBarraB=0;
			}
			if(chiave == KeyEvent.VK_W || chiave == KeyEvent.VK_S){
				velocitaBarraA=0;
			}	
		}
                
                
		@Override
		public void keyTyped(KeyEvent e1) {}
		
		public void ChiVince(Graphics g){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Times New Roman",Font.ITALIC,42));
			g.drawString(giocatoreVincente +" vince " + Integer.toString(s.gameP1) + " - " + Integer.toString(s.gameP2), larghezzaSchermo/2-200, altezzaSchermo/2 - 100);
			g.setColor(Color.WHITE);
			g.drawString("Premi [ESC] per uscire dal gioco", larghezzaSchermo/2-280, altezzaSchermo/2 - 50);
		}
		
		public void ChiStartaMatch(Graphics g){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Times new roman",Font.ITALIC,42));
			g.drawString("Premi [SPAZIO] per giocare", larghezzaSchermo/2-280, altezzaSchermo/2 - 100);
		}
	}
	
}
