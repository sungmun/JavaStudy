package Serversynchronization;

public interface MessageType {
	int MAP_MESSAGE = 0x1; 			// ��
	int USER_LIST_MESSAGE = 0x2;	// ���� ���� ���
	int GAMEOVER_MESSAGE = 0x3;		// ���� ����
	int WAITING_ROOM_CONNECT = 0x4;	// ���� ����
	int USER_SELECTING = 0x5;		// ����� ��û��
	int BE_CHOSEN = 0x6;			// ����� ��û ����
	int LOGIN = 0x8;				// �α���
	int LOGOUT = 0x9;				// �α׾ƿ�
	int WAR_ACCEPT = 0xA;			// ��� ����
	int WAR_DENIAL = 0XB;			// ��� ����
	int USER_SERIAL_NUM = 0xC;		// ������ �������� ������� �ø����ȣ�� �����ش�.
	int WAR_START=0xD;				// ������
	int WAR_END=0xE;				// ��᳡
	int CONNECT=0xF;				// ����
}
