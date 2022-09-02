package version_10_20220205;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//메인 클래스
public class GalagonGame extends JPanel implements KeyListener, Runnable {
	//필드
	private boolean running = true;
	private boolean endgamemg;
	private boolean winmg;
	
	private ArrayList sprites = new ArrayList();
	private Common starship;
	private Background background;
	
	private BufferedImage alienImage;
	private BufferedImage shotImage;
	private BufferedImage shipImage;
	private BufferedImage alienShotImage;
	private BufferedImage boomImage;
	
	private JLabel score1;
	private int score2;
	
	private JLabel start1;
	private JLabel start2;
	
	private JLabel guide1;
	private JLabel guide2;
	
	private JLabel win;
	private JLabel finalScore;
	
	private JLabel Lose;
	private JLabel end;
	
	private JLabel finishStage;
	protected int stage = 0;
	
	//생성자
	public GalagonGame() {
		JFrame frame = new JFrame("Galaga Game");
		frame.setSize(800, 900);
		frame.add(this, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//띄워지는 게임 창을 사용자의 모니터 사이즈에서 정 중앙에 오도록 설정
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((int)((screenSize.getWidth() / 2) - (frame.getWidth()/2)), (int)((screenSize.getHeight() / 2) - (frame.getHeight()/2)));
		
		//배경
		background = new Background(this);
		background.addStar();
		setBackground(Color.BLACK);
		
		//각 라벨
		setLayout(null);
		Font font1 = new Font("Gothic", Font.BOLD, 40);
		Font font2 = new Font("Arial", Font.PLAIN, 20);
		Font font3 = new Font("Dialog", Font.ITALIC, 70);
		Font font4 = new Font("Dialog", Font.PLAIN, 30);
		Font font5 = new Font("Dialog", Font.ITALIC, 90);
		Font font6 = new Font("Arial", Font.PLAIN, 50);
		
		//좌측 상단 점수
		score1 = new JLabel("");
		score1.setForeground(Color.YELLOW);
		score1.setFont(font1);
		score1.setSize(280, 45);
		score1.setLocation(10, 10);
		add(score1);

		//게임 초기 화면 게임명
		start1 = new JLabel("GALAGON GAME");
		start1.setForeground(Color.RED);
		start1.setFont(font3);
		start1.setSize(570, 75);
		start1.setLocation((getWidth()/2) - (start1.getWidth()/2), (getHeight()/2) - start1.getHeight() - 50);
		add(start1);

		//게임 초기 화면 게임 시작 버튼 유도
		start2 = new JLabel("Please press the space bar");
		start2.setForeground(Color.WHITE);
		start2.setFont(font4);
		start2.setSize(360, 35);
		start2.setLocation((getWidth()/2) - (start2.getWidth()/2), (getHeight()/2) + 50);
		add(start2);

		//게임 초기 화면 유닛 움직임 방법 설명
		guide1 = new JLabel("Move : left(←) & right(→)");
		guide1.setForeground(Color.WHITE);
		guide1.setFont(font2);
		guide1.setSize(230, 25);
		guide1.setLocation((getWidth()/2) - (guide1.getWidth()/2), getHeight() - guide1.getHeight() - 40);
		add(guide1);
		
		//게임 초기 화면 유닛 공격 방법 설명
		guide2 = new JLabel("Attack : space bar");
		guide2.setForeground(Color.WHITE);
		guide2.setFont(font2);
		guide2.setSize(170, 25);
		guide2.setLocation((getWidth()/2) - (guide2.getWidth()/2), getHeight() - guide2.getHeight() - 10);
		add(guide2);
		
		//게임 승리 시 승리 구문
		win = new JLabel("");
		win.setForeground(Color.RED);
		win.setFont(font5);
		win.setSize(470, 95);
		win.setLocation((getWidth()/2) - (win.getWidth()/2), (getHeight()/2) - win.getHeight() - 200);
		add(win);
		
		//게임 패배 시 패배 구문
		Lose = new JLabel("");
		Lose.setForeground(Color.BLUE);
		Lose.setFont(font5);
		Lose.setSize(470, 95);
		Lose.setLocation((getWidth()/2) - (Lose.getWidth()/2), (getHeight()/2) - Lose.getHeight() - 200);
		add(Lose);
		
		//게임 종료(승리or패배) 시 안내 구문
		end = new JLabel("");
		end.setForeground(Color.WHITE);
		end.setFont(font4);
		end.setSize(480, 35);
		end.setLocation((getWidth()/2) - (end.getWidth()/2), getHeight() - end.getHeight() - 70);
		add(end);
		
		//게임 종료(승리or패배) 시 최종 점수
		finalScore = new JLabel("");
		finalScore.setForeground(Color.YELLOW);
		finalScore.setFont(font6);
		finalScore.setSize(330, 55);
		finalScore.setLocation((getWidth()/2) - (finalScore.getWidth()/2), (getHeight()/2) + 150);
		add(finalScore);
		
		//게임 종료(승리or패배) 시 최종 스테이지
		finishStage = new JLabel("");
		finishStage.setForeground(Color.GREEN);
		finishStage.setFont(font6);
		finishStage.setSize(300, 55);
		finishStage.setLocation((getWidth()/2) - (finishStage.getWidth()/2), (getHeight()/2) - (finishStage.getHeight()/2));
		add(finishStage);
		
		//각 이미지 불러오기
		try {
			shotImage = ImageIO.read(new File("fireball.gif"));
			shipImage = ImageIO.read(new File("ship2.png"));
			alienImage = ImageIO.read(new File("sprite_rotmg_enemy0.png"));
			alienShotImage = ImageIO.read(new File("Attack.png"));
			boomImage = ImageIO.read(new File("boom.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		//키 이벤트 위해 포커스 지정
		this.requestFocus();
		addKeyListener(this);
		
		//배경음
		Sound bs = new Sound("wind.wav", true, 0);
		//배경음 종료 필요 시 원하는 위치에 아래 구문 삽입
		//bs.close();
	}
	
	//우주선과 적 생성 및 저장 메소드
	private void initSprites() {
		starship = new StarShip(this, shipImage, (getWidth()/2) - (shipImage.getWidth()/2), getHeight() - shipImage.getHeight());
		sprites.add(starship);
		for(int y = 0; y < 5; y++) {
			for(int x = 0; x < 12; x++) {
				Common alien = new Alien(this, alienImage, 50 + (x * 50), 50 + (y * 30));
				sprites.add(alien);
			}
		}
	}
	
	//게임 시작
	private void startGame() {
		endgamemg = false;
		winmg = false;
		win.setText("");
		end.setText("");
		finalScore.setText("");
		finishStage.setText("");
		sprites.clear();
		initSprites();
		Thread start = new Thread(this);
		start.start();
	}
	
	//게임 종료
	public void endGame() {
		System.out.println("endGame");
		System.exit(0);
	}
	
	//게임 패배
	public void endGamemg() {
		endgamemg = true;
		Lose.setText("Game Lose");
		sprites.clear();
		finishStage.setText("Stage : " + stage);
		finalScore.setText("Score : " + score2);
		score1.setText("");
		end.setText("ENTER 키를 누르면 종료됩니다. ");
	}
	
	//게임 승리
	public void Winner() {
		winmg = true;
		win.setText("Game Win");
		sprites.clear();
		finishStage.setText("Stage : " + stage);
		finalScore.setText("Score : " + score2);
		score1.setText("");
		end.setText("ENTER 키를 누르면 다음 스테이지");
	}
	
	//유닛 삭제
	public void removeSprite(Common sprite) {
		if(sprite instanceof Alien) {
			score2 += 50;
			Common boom = new Boom(this, boomImage, sprite.getX(), sprite.getY());
			sprites.add(boom);
		}
		sprites.remove(sprite);
	}
	
	//우주선 탄환 생성
	public void fire() {
		Common shot = new Shot(this, shotImage, starship.getX() + (shipImage.getWidth()/2) - (shotImage.getWidth()/2), starship.getY() - 30);
		sprites.add(shot);
		
		Sound ss = new Sound("Laser_Shot.wav", true, 1);
		ss.start();
		
	}

	//적 탄환 생성
	public void Attack() {
		int dx = 0;
		int get = (int) (Math.random() * sprites.size());
		
		if(sprites.get(get) instanceof Alien) {
			Common enemy = (Common)sprites.get(get);
			if(starship.x > enemy.x) {
				dx = (int) (starship.x / enemy.x);
			}
			else if(starship.x < enemy.x) {
				dx = (int) -(enemy.x / starship.x);
			}
			if(dx > 3) {
				dx = 3;
			}
			Common Attack = new AlienShot(this, alienShotImage, enemy.getX() + (alienImage.getWidth()/2) - (alienShotImage.getWidth()/2), enemy.getY() + alienImage.getHeight(), dx);
			sprites.add(Attack);
		}
	}
	
	//그리기 오버라이딩
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		background.draw(g);
		
		for(int i = 0; i < sprites.size(); i++) {
			Common sprite = (Common) sprites.get(i);
			sprite.draw(g);
		}
	}
	
	//게임 진행
	public void run() {
		stage++;
		
		while(running) {
			int alienCount = 0;
			
			background.move();
			
			for(int i = 0; i < sprites.size(); i++) {
				Common sprite = (Common) sprites.get(i);
				sprite.move();
			}
			for(int p = 0; p < sprites.size(); p++) {
				for(int s = p+1; s < sprites.size(); s++) {
					Common me = (Common) sprites.get(p);
					Common other = (Common) sprites.get(s);
					//두 객체가 부딪혔으면
					if(me.checkCollision(other)) {
						//각 객체에 정의된 handleCollision 메소드로
						//적과 부딪혔는지 확인하여 그에 맞는 동작을 실행
						me.handleCollision(other);
						other.handleCollision(me);
					}
				}
			}
			
			score1.setText("Score : " + score2);
			
			repaint();
			
			for(int i = 0; i < sprites.size(); i++) {
				Common sprite = (Common) sprites.get(i);
				if(sprite instanceof Alien)
					alienCount++;
			}

			if(endgamemg) {
				break;
			}
			
			if(alienCount == 0 && !endgamemg) {
				Winner();
				break;
			}
			
			try {Thread.sleep(10);} catch(Exception e) {}
		}
	}
	
	//키 누름 이벤트
	@Override
	public void keyPressed(KeyEvent e) {
		if(!endgamemg && !winmg) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT && (stage != 0)) {
				starship.setDx(-3);
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT && (stage != 0)) {
				starship.setDx(3);
			}
			
			if((e.getKeyCode() == KeyEvent.VK_SPACE) && (stage != 0)) {
				fire();
			}
			
			if((e.getKeyCode() == KeyEvent.VK_SPACE) && (stage == 0)) {
				remove(start1);
				remove(start2);
				remove(guide1);
				remove(guide2);
				startGame();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER && endgamemg) {
			endGame();
			}
		if(e.getKeyCode() == KeyEvent.VK_ENTER && winmg) {
			startGame();
		}
		
	}
	
	//키 뗌 이벤트
	@Override
	public void keyReleased(KeyEvent e) {
		if(!endgamemg && !winmg) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT  && (stage != 0)) {
				starship.setDx(0);
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT  && (stage != 0)) {
				starship.setDx(0);
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	//프로그램 실행
	public static void main(String[] args) {
		GalagonGame g = new GalagonGame();
	}
}
