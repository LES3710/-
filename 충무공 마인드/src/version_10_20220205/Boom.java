package version_10_20220205;

import java.awt.Image;

//적 폭발 클래스 (우주선 탄환에 맞았을 때 생기는 폭발 이미지)
public class Boom extends Common {
	//필드
	private GalagonGame game;
	private int count;
	
	//생성자
	public Boom(GalagonGame game, Image image, int x, int y) {
		super(image, x, y);
		this.game = game;
	}
	
	//적 폭발 움직임 오버라이딩
	@Override
	public void move() {
		count++;
		if(count == 10) {
			game.removeSprite(this);
		}
	}
}

