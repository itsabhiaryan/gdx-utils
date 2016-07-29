package com.nayragames.gdxutils;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 24-07-2016
 *
 */
public class Comment {

    //important line		//final AObject localCandy1 = AObject.getAnimation(objectGroup, Position.makePosition(Cube.getCube(i, j).getPosition().x, Main.startY + Main.COLUMN * Main.cellSide + Main.cellSide / 2f + fillCubeStack[j] * Main.cellSide), color, 0, 1.0F);
    //pool.origin=Position.makePosition(Cube.getCube(i, j).getPosition().x, Main.startY + Main.COLUMN * Main.cellSide + Main.cellSide / 2f + fillCubeStack[j] * Main.cellSide);
    //final AObject localCandy=pool.obtain();

    //int color = Main.random.nextInt(Main.OBJECT_COLOR);
    //final AObject localCandy = AObject.make(objectGroup, Position.makePosition(Cube.getCube(i, j).getPosition().x, Main.startY + Main.COLUMN * Main.cellSide + Main.cellSide / 2f + fillCubeStack[j] * Main.cellSide), color, 0, 1.0F);

    					/*for (Cube localCube : localList) {

								if (Cube.hasObject(localCube) && localCube.getObject().type ==AObject.TYPE_BOMB && !skipArrayList.contains(localCube)) {
									b=true;
									score=Blast.boomBlast(localCube, score);
								}

								if (Cube.hasObject(localCube) && localCube.getObject().type == AObject.TYPE_HORIZON && !skipArrayList.contains(localCube)) {
									b = true;
									score=Blast.rowBlast(localCube, score);

								} else if (Cube.hasObject(localCube) && localCube.getObject().type == AObject.TYPE_VERTICAL && !skipArrayList.contains(localCube)) {
									b = true;
									score=Blast.columnBlast(localCube, score);

								}

								Brick brick=Brick.getBrick(localCube.getPosition());
								if(brick!=null) {
									brick.setInitialVelocity();
									score+=Brick.BRICK_VALUE;
								}

								Lock lock=Lock.getLock(localCube.getPosition());
								if(lock!=null) {
									lock.setInitialVelocity();
									score+=Lock.LOCK_VALUE;
								}

								if(!retrieved.contains(localList)&& score < 1000)
									score=Barrier.checkSurroundedBarrier(localCube, score);

								if (localList.size()==Constants.MATCH_AMOUNT &&(Cube.hasObject(localCube)||Cube.hasLockedObject(localCube)) && localCube.getObject().type == AObject.TYPE_COMMON) {

									for(final TargetObject localtarget:targetObject) {
										if (Cube.hasObject(localCube)&&localtarget.color == localCube.getObject().color) {

											final AObject localCandy = localCube.getObject();
											localCandy.matchType = AObject.MATCH_TYPE_HIDE;

											localtarget.isBucketMove = false;
											GameScreen.objectArrayList.remove(localCandy);

											double theta = Math.atan2(Main.height - (localtarget.bucket.getY() + localtarget.bucket.getHeight() / 2f) - (Main.height - localCube.getPosition().y), localtarget.bucket.getX() - localCube.getPosition().x);
											double angle = Math.toDegrees(theta);
											if (angle < 0) {
												angle += 360;
											}


											localCube.hasWhat = Cube.HAS_NOTHING;
											topMatrixGroup.addActor(localCandy);
											b=true;

											Vector2 cp[] = null;

											float lengthx = localtarget.bucket.getX() - localCandy.getPosition().x;
											float lengthy = localtarget.bucket.getY() + localtarget.bucket.getHeight() / 2f - localCandy.getPosition().y;

											cp = Path.getBucketPath(lengthx, lengthy, angle);

											if(cp!=null){
												localtarget.currentCount++;
												Bezier<Vector2> bezier=new Bezier<Vector2>(cp);

												float time=(bezier.approxLength(30)/Main.cellSide)*.15f;
												MoveAlongAction action1 = MoveAlongAction.obtain(bezier, time);
												action1.setRotate(true);

												MoveAlongAction action2 = MoveAlongAction.obtain(new Bezier<Vector2>(cp), time);
												action2.setRotate(true);

												final ParticleActor particleActor=addStarOnBucket(Position.makePosition(localCandy.getX() + localCandy.getWidth() / 2, localCandy.getY() + localCandy.getHeight() / 2));
												particleActor.addAction(action2);

												localCandy.addAction(Actions.parallel( Actions.scaleTo(.25f, .25f, 3f), Actions.sequence(action1, Actions.run(new Runnable() {
												@Override
												public void run() {
														localtarget.isBucketMove = true;
														particleActor.addAction(Actions.sequence(Actions.delay(.25f), Actions.run(new Runnable() {
																@Override
																public void run() {
																	particleActor.removeEffect();
																}
														})));

														localtarget.isUpdate=true;
														localCandy.remove();
														localtarget.bucket.addAction(Actions.sequence(Actions.scaleTo(1.1F, 1.1F, 0.1F), Actions.scaleTo(1.0F, 1.0F, 0.1F)));

													}
												}))));
											}else
												Gdx.app.log(TAG,"cp null because"+angle);
										}
									}

									if(Cube.hasObject(localCube)&&localCube.getObject().matchType == AObject.UN_MATCHED)
										localCube.getObject().matchType = AObject.MATCH_TYPE_BLAST;

								}


							}*/

							/*if (localList.size() == Constants.MATCH_AMOUNT+2) {
								AObject victim=null;
								for (Cube localCube : localList) {
									if (localCube.hasWhat == Cube.HAS_OBJECT && localCube.getObject().equals(PlayListener.firstObject))
									victim=PlayListener.firstObject;
									else if (localCube.hasWhat >= Cube.HAS_OBJECT && localCube.getObject().equals(PlayListener.secondObject))
									victim=PlayListener.secondObject;
								}
								if(victim!=null)
								replaceKingCandy(victim,localList);
								else
								replaceKingCandy(localList.get(AGame.random.nextInt(localList.size())).getObject(),localList);

								b=true;
							}*/



							/*if (localList.size() == Constants.MATCH_AMOUNT+1) {
								AObject victim = null;
								for (Cube localCube : localList) {

									if (localCube.hasWhat == Cube.HAS_OBJECT && localCube.getObject().equals(PlayListener.firstObject))
										victim=PlayListener.firstObject;
									else
									if (localCube.hasWhat == Cube.HAS_OBJECT && localCube.getObject().equals(PlayListener.secondObject))
										victim=PlayListener.secondObject;
								}

								if(victim!=null)
								replaceHigherCandy(victim, AObject.TYPE_VERTICAL, localList);
								else
								replaceHigherCandy(localList.get(AGame.random.nextInt(localList.size())).getObject(), AObject.TYPE_VERTICAL, localList);

								b=true;

							}*/

	/*public static int getBlank(Cube cube,int move) {
		int currentCount=0;
		int row=cube.row-move;
		while(!Cube.getCube(row,cube.column).isVisible()) {

			currentCount++;
			if(row>0)
				row--;
			else
				break;
		}

		System.out.println("getBlank"+currentCount);
		return  currentCount;
	}*/

	/*public static int getLastBlank(Cube cube) {
		int row=Main.COLUMN-1;
		for (int i=Main.COLUMN-1;i>=cube.row;i--)
			if(Cube.getCube(i,cube.column).isVisible()) {
				row = i;
				//System.out.println("return by getLast blank"+row);
				return row;

			}
		return row;
	}*/

	/*public static boolean isAllStatic(Cube cube) {
		boolean b=true;
		int currentCount=0;
		int no=0;
		for (int i = Main.COLUMN-1; i >=cube.row; i--) {
			for (int j = Main.COLUMN-1; j >=0; j--) {
				currentCount++;
				if(currentCount<=((Main.COLUMN-cube.row)*Main.COLUMN)-cube.column) {
					System.out.println(i + "AND" + j);
					if (Cube.getCube(i, j).isVisible() && Cube.getCube(i, j).hasWhat == Cube.HAS_NOTHING) {
						b = false;
						no++;
						System.out.println("isAllStatic" + b);
					}
				}
			}
		}

		System.out.println("return by isAllStatic"+b+no);

			if(no>1)
				return false;
			else
				return true;
		//return b;
	}*/

	/*private static int getTopBlank(Cube cube) {
		int row=0;
		for (int i =AGame.COLUMN-1 ; i >= cube.row; i--) {
			if (!Cube.getCube(i,cube.column).isVisible())
				row++;
		}
		return row;
	}*/

	/*private static int getLastBlankCubeVertical() {
		int row=Main.COLUMN-1;
		for (int i = Main.COLUMN-1; i >=0 ; i--) {
			for (int j = 0; j < Main.COLUMN; j++) {
				if (Cube.getCube(i, j).isVisible()) {
					row = i;
					return row;
				}
			}
		}
		return row;
	}*/

	/*private static int getLastBlankCubeHorizon(Cube cube) {
			int column=0;
		try {
			*//*	for (int i = AGame.COLUMN - 1; i >= 0; i--)
					if(Cube.getCube(getLastBlankCubeVertical(),i).isVisible())
						if (Cube.getCube(getLastBlankCubeVertical(),i).hasWhat==Cube.HAS_NOTHING&&Cube.getCube(getLastBlankCubeVertical(), i).getObject() == null) {
							column = i;
							break;
						}*//*
*//*
			for (int i = AGame.COLUMN - 1; i >= 0; i--)
				if(Cube.getCube( getLastBlank(cube),i).isVisible())
					if (Cube.getCube( getLastBlank(cube),i).hasWhat==Cube.HAS_NOTHING&&Cube.getCube(getLastBlankCubeVertical(), i).getObject() == null) {
						column = i;
						break;
					}
*//*
			for (int i = 0; i < AGame.COLUMN; i++)
					if(Cube.getCube(7,i).isVisible())
						if (Cube.getCube(7,i).hasWhat==Cube.HAS_NOTHING&&Cube.getCube(7, i).getObject() == null) {
							column = i;
							break;
						}
		}catch (Exception e) {
				Gdx.app.log(TAG,"Exception in getLastBlankCubeHorizon",e);
			}


			System.out.println("return by getLastCubeHorizon"+column);
			return column;
	}*/


	//private Vector3 translateScreenToWorldCoordinates(int x, int y) {
		//return 	camera.unproject(touchPoint.set(x, y, 0));
	//}


	/*private static void checkTarget(AObject candy) {

		for(TargetObject target:targetObject)
			if(candy.type==AObject.TYPE_COMMON) {
				if (candy.type == target.type && candy.color == target.color)
					if (target.currentCount > 0) {
						target.currentCount--;
						target.label.setText(String.valueOf(target.currentCount));
					}
			}
			else if(candy.type>=AObject.TYPE_SPECIAL){


		}
	}*/

	/*public static void swapObject(final AObject paramCandy1, final AObject paramCandy2) {

		Position localGpoint1 = paramCandy1.getPosition();
		Position localGpoint2 = paramCandy2.getPosition();


		paramCandy1.addAction(Actions.sequence(Actions.moveTo(localGpoint2.x-paramCandy1.getWidth()/2f, localGpoint2.y-paramCandy1.getHeight()/2f, 0.25F), Actions.run(new Runnable() {
			public void run() {

				Cube oldCube=paramCandy1.getCube();
				int color = paramCandy1.color;
			Position localAPoint = oldCube.getPosition();
			AObject.remove(oldCube.row, oldCube.column);

			Brick brick = Brick.getBrick(oldCube.getPosition());
			if (brick != null)
				brick.setInitialVelocity();

			int type[] = new int[]{AObject.TYPE_HORIZON, AObject.TYPE_VERTICAL};

			AObject localCandy = AObject.make(GameScreen.objectGroup, GameScreen.size, localAPoint, color, type[AGame.random.nextInt(type.length)], .1F);
			localCandy.matchType=AObject.MATCH_TYPE_BLAST;
			localCandy.addAction(Actions.scaleTo(1.0F, 1.0F, .5f, Interpolation.swingOut));
			GameScreen.objectArrayList.add(localCandy);

				if(totalHit==5){
					if(checkSeries()){
						for(AObject candy:GameScreen.objectArrayList)
							if(candy.matchType > AObject.UN_MATCHED) {
								if(candy.matchType == AObject.MATCH_TYPE_BLAST) {
									//AnimationBuilder.getAnimation(GameScreen.animGroup, 0, candy.getPosition(), AnimationBuilder.TYPE_BOOM_COMMON);
									candy.getCube().hasWhat = Cube.HAS_NOTHING;
									candy.remove();
									GameScreen.objectArrayList.remove(candy);
								}
							}

						GameScreen.isCheckAnim=true;
						PlayListener.isGet = false;
					}
				}


			}
		})));

		paramCandy2.addAction(Actions.sequence(Actions.moveTo(localGpoint1.x-paramCandy2.getWidth()/2f, localGpoint1.y-paramCandy2.getHeight()/2f, 0.25F), Actions.run(new Runnable() {
			public void run() {

			}
		})));



	}*/

	/*	private static void replaceBoomCandy(AObject oldCandy) {
		try {
			if (oldCandy != null) {
				Cube oldCube = oldCandy.getCube();
				Brick brick = Brick.getBrick(oldCube.getPosition());
				if (brick != null)
					brick.setInitialVelocity();
				skipArrayList.add(oldCube);
				int color = oldCandy.color;
				Position localAPoint = oldCube.getPosition();
				AObject.remove(oldCube.row, oldCube.column);

				AObject localCandy = AObject.make(objectGroup, size,localAPoint, color, AObject.TYPE_BOMB, .1F);
				localCandy.addAction(Actions.scaleTo(1.0F, 1.0F, .5f, Interpolation.swingOut));
				objectArrayList.add(localCandy);
			}

		}
		catch (Exception e){
			Gdx.app.log(TAG,"Exception in replaceBoomCandy method",e);
		}
	}*/


		/*					if(localList.size()== Constants.MATCH_AMOUNT)
									for(Cube localCube:localList)
										if(horizontalList.contains(localCube)) {
											if (localCube.hasWhat == Cube.HAS_OBJECT && localCube.getObject().equals(PlayListener.firstObject))
												replaceBoomCandy(PlayListener.firstObject);
											else if (localCube.hasWhat == Cube.HAS_OBJECT && localCube.getObject().equals(PlayListener.secondObject))
												replaceBoomCandy(PlayListener.secondObject);
										}
			*/



		/*if (oldCandy != null) {
			*//*	Cube oldCube = oldCandy.getCube();
				Brick brick = Brick.getBrick(oldCube.getPosition());
				if (brick != null)
					brick.setInitialVelocity();
				skipArrayList.add(oldCube);*//*

				*//*int color = oldCandy.color;
				Position localAPoint = oldCube.getPosition();
				AObject.remove(oldCube.row, oldCube.column);*//*

				AObject localCandy = AObject.make(objectGroup, size,localAPoint, color, AObject.TYPE_BOMB, .1F);
				localCandy.addAction(Actions.scaleTo(1.0F, 1.0F, .5f, Interpolation.swingOut));
				objectArrayList.add(localCandy);
			}*/


	/*
    Gdx.app.log(TAG, cube1 + "AND" + cube2);

    //final double distance = Position.findDistance(cube1.getPosition(), cube2.getPosition());

    final double distance = cube1.getPosition().dst(cube2.getPosition());

    System.out.println("Distance is" + distance / (Main.cellSide * 1.5f));

    float di = (float) (distance / (6 * Main.cellSide));


    double theta = Math.atan2(((cube2.getPosition().y)) - (cube1.getPosition().y), cube2.getPosition().x - cube1.getPosition().x);
    double angle = Math.toDegrees(theta);
    if (angle < 0)
    angle += 360;

    Position point = null;
    if (cube1.getPosition().x <= cube2.getPosition().x && cube1.getPosition().y <= cube2.getPosition().y) {
        point = Position.makePosition(cube1.getPosition().x + Math.abs(cube1.getPosition().x - cube2.getPosition().x) / 2f, cube1.getPosition().y + Math.abs(cube1.getPosition().y - cube2.getPosition().y) / 2f);
        System.out.println("firstquad");
    } else if (cube1.getPosition().x >= cube2.getPosition().x && cube1.getPosition().y <= cube2.getPosition().y) {
        point = Position.makePosition(cube1.getPosition().x - Math.abs(cube1.getPosition().x - cube2.getPosition().x) / 2f, cube1.getPosition().y + Math.abs(cube1.getPosition().y - cube2.getPosition().y) / 2f);
        System.out.println("secondquard");
    } else if (cube1.getPosition().x >= cube2.getPosition().x && cube1.getPosition().y >= cube2.getPosition().y) {
        point = Position.makePosition(cube1.getPosition().x - Math.abs(cube1.getPosition().x - cube2.getPosition().x) / 2f, cube1.getPosition().y - Math.abs(cube1.getPosition().y - cube2.getPosition().y) / 2f);
        System.out.println("thirdquard");
    } else if (cube1.getPosition().x <= cube2.getPosition().x && cube1.getPosition().y >= cube2.getPosition().y) {
        point = Position.makePosition(cube1.getPosition().x + Math.abs(cube1.getPosition().x - cube2.getPosition().x) / 2f, cube1.getPosition().y - Math.abs(cube1.getPosition().y - cube2.getPosition().y) / 2f);
        System.out.println("fourthquard");
    }

    AObject object = cube1.getObject();
    AObject object1 = cube2.getObject();

    double angle1 = Position.makePosition(object.getX() + object.getWidth() / 2, object.getY() + object.getHeight() / 2).angle(Position.makePosition(object1.getX() + object1.getWidth() / 2f, object1.getY() + object1.getHeight() / 2f));

    System.out.println("ANGLE IS" + angle1);
    System.out.println("Value is" + Math.abs(cube1.getPosition().x - cube2.getPosition().x) / 2f + "And");
    System.out.println("SCALE" + di);
    GameScreen.aniActor = AnimationBuilder.getAnimation(GameScreen.topMatrixGroup, di, point, AnimationBuilder.TYPE_LIGHTING, (float) angle + 90);
*/


}
