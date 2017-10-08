package Serversynchronization;

public interface MessageType {
	int LOGIN                = 0x0;	
	int USER_SERIAL_NUM      = 0x1;
	int WAITING_ROOM_CONNECT = 0x2;	
	int USER_LIST_MESSAGE    = 0x3;	
	int USER_SELECTING       = 0x4;
	int BE_CHOSEN            = 0x5;	
	int WAR_ACCEPT           = 0x6;
	int WAR_DENIAL           = 0x7;
	int WAR_START            = 0x8;	
	int WAR_END              = 0x9;	
	int LOGOUT               = 0xA;	
	int GAMEOVER_MESSAGE     = 0xB;	
	
	int USER_MESSAGE         = 0x10;
	int MAP_MESSAGE          = 0x11;
	int NEXT_BLOCK_MESSAGE   = 0x12;
	int SAVE_BLOCK_MESSAGE   = 0x13; 	
	int LEVEL_MESSAGE        = 0x14; 	
	int SCORE_MESSAGE        = 0x15; 	
}
