import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PathFirst {
    private int SIZE = 0;
    final int STEP_MIX = 4;
    int[] randomX;
    int randomNo;
    Random rnd;
    int startX, finishX;
    List<Koordinat> mainEmpty, emptyCoordinate;
    char[][] maze;

    public PathFirst(int size) {
    	if(size % 2 == 0) {
            SIZE = size + 1;
    	} else {
            SIZE = size;    		
    	}
        rnd = new Random();
        maze = new char[SIZE][SIZE];
        randomNo = 1;
        randomX = new int[(SIZE / 2) + 1];
        emptyCoordinate = new ArrayList<>();
        mainEmpty = new ArrayList<>();
        startX = rnd.nextInt(SIZE - 3) + 1;
        randomX[0] = startX;
        finishX = rnd.nextInt(17) + 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == 0 || j == 0 || i == SIZE - 1 || j == SIZE - 1) {
                    if (i == 0 && j == finishX) {
                        addSpace(i, j);
                    } else if (i == SIZE - 1 && j == startX) {
                        addSpace(i, j);
                    } else {
                        addWall(i, j);
                    }
                } else addSpace(i, j);
            }
        }
        shuffle();
    }

    private void fillSide(int y) {
        addWall(y, randomX[randomNo] + 1);
        addWall(y, randomX[randomNo] - 1);
        for (int i = 1; i < SIZE - 1; i++) {
            if (i != randomX[randomNo]) addWall(y - 1, i);
        }
    }

    private void fillRight(int y) {
        int x = rnd.nextInt(100);
        if (x < 33) {
            addWall(y, randomX[randomNo - 1] - 1);
            addWall(y, randomX[randomNo] + 1);
        }
        int topY = y - 1;
        for (int i = 1; i < SIZE - 1; i++) {
            if (i != randomX[randomNo]) addWall(topY, i);
        }
    }

    private void fillLeft(int y) {
        int x = rnd.nextInt(100);
        if (x < 33) {
            addWall(y, randomX[randomNo] - 1);
            addWall(y, randomX[randomNo - 1] + 1);
        }
        int topY = y - 1;
        for (int i = 1; i <= SIZE - 2; i++) {
            if (i != randomX[randomNo]) addWall(topY, i);
        }
    }

    private void addWall(final int x, final int y) {
        maze[x][y] = '#';
    }

    private void addSpace(int x, int y) {
        maze[x][y] = ' ';
    }

    private void shuffle() {
        Koordinat lastCoordinate = new Koordinat(SIZE - 1, startX);
        for (int i = SIZE - 2; i >= 1; i--) {
            if (i % 2 == 1) {
                if (randomNo < SIZE / 2) randomX[randomNo] = rnd.nextInt(SIZE - 2) + 1;
                else randomX[randomNo] = finishX;
                if (randomX[randomNo] > randomX[randomNo - 1]) {
                    fillRight(i);
                } else if (randomX[randomNo] < randomX[randomNo - 1]) {
                    fillLeft(i);
                } else {
                    fillSide(i);
                }
                mainEmpty.add(new Koordinat(i - 1, randomX[randomNo]));
                randomNo++;
            } else {
                Koordinat mainCoordinate = new Koordinat();
                for (Koordinat k : this.mainEmpty) {
                    if (k.getY() == i) {
                        mainCoordinate = k;
                        break;
                    }
                }
                if (mainCoordinate.getX() > lastCoordinate.getX() && lastCoordinate.getY() != SIZE - 1 && mainCoordinate.getY() != 0) {
                    mix(lastCoordinate, mainCoordinate, lastCoordinate.getY());
                } else if (mainCoordinate.getX() <= lastCoordinate.getX() && lastCoordinate.getY() != SIZE - 1 && mainCoordinate.getY() != 0) {
                    mix(mainCoordinate, lastCoordinate, lastCoordinate.getY());
                }
                lastCoordinate = mainCoordinate;
            }
        }
    }

    private void mix(Koordinat leftCoordinate, Koordinat rightCoordinate, int y) {
        int sag = rightCoordinate.getX() + 3;
        int start = rightCoordinate.getX() + 1, wallX;
        for (int j = sag; j < SIZE - 1; j += STEP_MIX) {
            maze[y][j] = ' ';
            wallX = rnd.nextInt(j - start - 1) + start + 1;
            if (wallX < SIZE && maze[y - 1][wallX - 1] != '#')
                maze[y - 1][wallX] = '#';
            start = j;
        }
        int sol = leftCoordinate.getX() - 3;
        int basla = sol + 2;
        for (int k = sol; k > 1; k -= STEP_MIX) {
            if (maze[y + 1][k] != '#')
                maze[y][k] = ' ';
            wallX = rnd.nextInt(basla - k - 1) + k + 1;
            if (wallX > 1 && maze[y - 1][wallX + 1] != '#')
                maze[y - 1][wallX] = '#';
            basla = k;
        }

    }

    public void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public char[][] getMaze() {
        return maze;
    }

}
