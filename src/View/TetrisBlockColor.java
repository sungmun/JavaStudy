package View;

import java.awt.Color;

public interface TetrisBlockColor {
	Color[] TETRINO_COLOR = { new Color(255, 0, 0, 0), // 투명 디폴트 값
			new Color(135, 206, 235), // 하늘색 - I
			new Color(0, 153, 255), // 파랑 - J
			new Color(248, 155, 0), // 주황 - L
			new Color(255, 255, 0), // 노랑색 - O
			new Color(0, 128, 0), // 초록색 - S
			new Color(102, 0, 153), // 보라 - T
			new Color(255, 0, 0), // 빨강 - Z
	};
}
