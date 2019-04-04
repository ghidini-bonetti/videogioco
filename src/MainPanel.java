import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel {
	punteggio s;
	
	public MainPanel(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS) );
		s = new punteggio();
		pannelloGioco p = new pannelloGioco();
		this.add(s);
		this.add(p);
	}
	
	public class punteggio extends JPanel {
		
		int larghezzaP=1280,altezzaP=220;
		int PunteggioP1=0, PunteggioP2=0;
		int setP1[] = new int[3];
		int setP2[] = new int[3];
		
		int gameP1=0,gameP2=0;
		
		public punteggio(){
                        this.setPreferredSize(new Dimension(larghezzaP,altezzaP));
			this.setBorder(BorderFactory.createLineBorder(Color.cyan));
			this.setBackground(Color.BLUE);	
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Times New Roman",Font.PLAIN,48));
			g.drawString("PING - PONG", larghezzaP/2- 120, altezzaP/2-50);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Times New Roman",Font.PLAIN,36));
			g.drawString("Giocatore 1", 30, 120);
			
			g.drawString("Giocatore 2", larghezzaP-190, 120);
			
			g.setColor(Color.green);
			g.fill3DRect(75, 150, 60, 50,true);
			g.fill3DRect(larghezzaP-80-60, 150, 60, 50,true);
			
			g.setColor(Color.WHITE);
			g.drawRect(75, 150, 60, 50);
			g.drawRect(larghezzaP-80-60, 150, 60, 50);
			
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(PunteggioP1), 90, 185);
			g.drawString(Integer.toString(PunteggioP2), larghezzaP-80-45, 185);
			
			g.setColor(Color.white);
			g.setFont(new Font("Times New Roman",Font.PLAIN,30));
			g.drawString("Giocatore 1", larghezzaP/2-250, 115);
			
			g.drawString("Giocatore 2", larghezzaP/2-250, 175);
			
			for(int i=1;i<=3;i++){
				g.setColor(Color.GREEN);
				g.fill3DRect(larghezzaP/2-170+70*i, 80, 70, 60,true);
				g.fill3DRect(larghezzaP/2-170+70*i, 140, 70, 60,true);
				g.setColor(Color.white);
				g.draw3DRect(larghezzaP/2-170+70*i, 80, 70, 60,true);
				g.draw3DRect(larghezzaP/2-170+70*i, 140, 70, 60,true);
			}
			
			for(int i=1;i<=3;i++){
				g.setColor(Color.white);
				g.setFont(new Font("Times New Roman",Font.PLAIN,46));
				g.drawString(Integer.toString(setP1[i-1]), larghezzaP/2-170+70*i+15, 125);
				g.drawString(Integer.toString(setP2[i-1]), larghezzaP/2-170+70*i+15, 185);
				
			}
			
			g.setColor(Color.GREEN);
			g.fill3DRect(larghezzaP/2-190+80*4, 80, 70, 60,true);
			g.fill3DRect(larghezzaP/2-190+80*4, 140, 70, 60,true);
			g.setColor(Color.white);
			g.draw3DRect(larghezzaP/2-190+80*4, 80, 70, 60,true);
			g.draw3DRect(larghezzaP/2-190+80*4, 140, 70, 60,true);
			
			g.setColor(Color.white);
			g.setFont(new Font("Times New Roman",Font.PLAIN,46));
			g.drawString(Integer.toString(gameP1), larghezzaP/2-190+70*4+55, 125);
			g.drawString(Integer.toString(gameP2), larghezzaP/2-190+70*4+55, 185);
			
		}
		
		
	}
	
	
	
	public class pannelloGioco extends JPanel implements ActionListener,KeyListener{
		
		int larghezzaSchermo=1280,altezzaSchermo=720;
		
		int larghezzaBarra=15,altezzaBarra=140;
		int BarraAx=20,BarraAy=altezzaSchermo/2 - altezzaBarra/2;
		int velocitaBarraA=10;
		
		int BarraBx=larghezzaSchermo-30,BarraBy=altezzaSchermo/2 - altezzaBarra/2;
		int velocitaBarraB=10;
		
		int raggioPalla=30;
		int pallaX=larghezzaSchermo/2-raggioPalla/2,pallaY=altezzaSchermo/2-raggioPalla/2;
		int velocitaBarraX=2,velocitaBarraY=2;
		
		int set=0;
		
		boolean inizio=false;
		boolean vincitore=false;
		
		String giocatoreVincente;
		
		boolean collisioneA=false;
		boolean collisioneB=false;
		
		
		Timer t;
		
		public pannelloGioco(){
			t= new Timer(1,this);
			this.setPreferredSize(new Dimension(larghezzaSchermo,altezzaSchermo));
			this.setBorder(BorderFactory.createLineBorder(Color.black));
			this.setBackground(Color.CYAN);
			this.addKeyListener(this);
			setFocusable(true);
			this.setFocusTraversalKeysEnabled(false);
			t.start();
			
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			g.setColor(Color.orange);
			g.fill3DRect(BarraAx, BarraAy, larghezzaBarra, altezzaBarra,true);
			
			g.fill3DRect(BarraBx, BarraBy, larghezzaBarra, altezzaBarra,true);
			
			g.setColor(Color.blue);
			g.fillOval(pallaX, pallaY, raggioPalla, raggioPalla);
			
			if(!inizio){
				drawStartGame(g);
			}
			
			if(vincitore){
				drawWinner(g);
				t.stop();
			}
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(inizio){
				pallaX += velocitaBarraX*5;
				pallaY += velocitaBarraY*5;
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
						s.PunteggioP1=7;
						s.PunteggioP2=7;
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
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_SPACE){
				inizio=true;
			}
			
			if(key == KeyEvent.VK_ESCAPE){
				System.exit(0);
			}
			
			if(key == KeyEvent.VK_UP){
				if(BarraBy > 0){
				velocitaBarraB = -2;
			}else
				velocitaBarraB =0;
			}
			
			if(key == KeyEvent.VK_DOWN){
					if(BarraBy+altezzaBarra < altezzaSchermo){
					velocitaBarraB = 2;
				}else
					velocitaBarraB = 0;
			}
			
				
			if(key == KeyEvent.VK_W){
				if(BarraAy > 0 ){
				velocitaBarraA = -2;
			}else
				velocitaBarraA =0;
			}
			
			
			if(key == KeyEvent.VK_S){
					if(BarraAy+altezzaBarra < altezzaSchermo){
					velocitaBarraA = 2;
				}else
					velocitaBarraA = 0;
			}
				
			if(key == KeyEvent.VK_R){
					 
					 BarraAx=20;BarraAy= altezzaSchermo/2 - altezzaBarra/2;
					 velocitaBarraA=0;
					
					 BarraBx=larghezzaSchermo-20;BarraBy=altezzaSchermo/2 - altezzaBarra/2;
					 velocitaBarraB=0;
					
					
					 pallaX=larghezzaSchermo/2-raggioPalla/2;pallaY=altezzaSchermo/2-raggioPalla/2;
					 velocitaBarraX=2;velocitaBarraY=2;
					
					 set=0;
					
					 inizio=false;
					 vincitore=false;
					
					 giocatoreVincente=null;
					 
					 s.PunteggioP1=0;
					 s.PunteggioP2=0;
					 s.gameP1=0;
					 s.gameP2=0;
					 for(int i=0;i<3;i++){
						 s.setP1[i]=0;
						 s.setP2[i]=0;
					 }
					 repaint();
					 s.repaint();
				}
			}
		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
				velocitaBarraB=0;
			}
			if(key == KeyEvent.VK_W || key == KeyEvent.VK_S){
				velocitaBarraA=0;
			}	
		}
		@Override
		public void keyTyped(KeyEvent arg0) {}
		
		public void drawWinner(Graphics g){
			g.setColor(Color.green);
			g.setFont(new Font("Times New Roman",Font.ITALIC,42));
			g.drawString(giocatoreVincente +" vince " + Integer.toString(s.gameP1) + " - " + Integer.toString(s.gameP2), larghezzaSchermo/2-200, altezzaSchermo/2 - 100);
			g.setColor(Color.ORANGE);
			g.drawString("Premi [ESC] per uscire dal gioco", larghezzaSchermo/2-280, altezzaSchermo/2 - 50);
		}
		
		public void drawStartGame(Graphics g){
			g.setColor(Color.orange);
			g.setFont(new Font("Times new roman",Font.ITALIC,42));
			g.drawString("Premi [SPAZIO] per giocare", larghezzaSchermo/2-280, altezzaSchermo/2 - 100);
		}
	}
	
}
