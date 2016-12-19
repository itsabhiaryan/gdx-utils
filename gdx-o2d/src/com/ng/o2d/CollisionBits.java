package com.ng.o2d;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30-07-2016
 *
 */

public class CollisionBits {
	
	public static final short 	
		Character	    = 0x0002,
 		SlideCharacter = 0x0003,
		Terrain 		= 0x0004,
		VisionSensor  	= 0x0008,
		TouchSensor     = 0x0010,
		Effects 		= 0x0020;
		
	public static final short

		Mask_Character	    = SlideCharacter | Terrain | VisionSensor,
		Mask_Bullet 		= Character | Terrain | VisionSensor | Effects,
		Mask_Terrain 		= Character | SlideCharacter | Terrain | VisionSensor | TouchSensor | Effects,
		Mask_VisionSensor	= Character | SlideCharacter | Terrain,
		Mask_TouchSensor    = Terrain,
		Mask_Effects 		= Terrain | SlideCharacter;
}
