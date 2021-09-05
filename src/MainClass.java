import java.util.Scanner;

public class MainClass {


    private final int SIZE = 33;

    private final Chronometer chronometer = new Chronometer();

    public static void main(String[] args) {
        MainClass mainClass = new MainClass();
        mainClass.printInfoMessage();
        int number = mainClass.enterAlgorithmNumber();
        switch (number) {
            case 1: 
            	mainClass.pathFirst();
            	break;
            case 2: 
            	mainClass.recursiveDivision();
            	break;
            case 3: 
            	mainClass.ellers();
            	break;
            case 4: 
            	mainClass.growingTree();
            	break;
            case 5: 
            	mainClass.kruskal();
            	break;
            case 6: 
            	mainClass.prims();
            	break;
            case 7: 
            	mainClass.binaryTree();
            	break;
            case 8: 
            	mainClass.backTracker();
            	break;
            default: System.err.println("Wrong Number");
        }
    }

    void printInfoMessage() {
        System.out.println("1. PathFirst");
        System.out.println("2. Recursive Division");
        System.out.println("3. Eller's");
        System.out.println("4. Growing Tree");
        System.out.println("5. Kruskal");
        System.out.println("6. Prims's");
        System.out.println("7. Binary Tree");
        System.out.println("8. BackTracker");
    }

    private int enterAlgorithmNumber() {
        System.out.println("Enter Algorithm Number");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        scanner.close();
		return number;
    }

    private void pathFirst() {
    	calculate(new Runnable() {
            @Override
            public void run() {
                new PathFirst(SIZE).printMaze();
            }
        });
    }
    
    private void calculate(final Runnable createMazeRunnable) {
        long[] timeArray = new long[1];
        for (int i = 0; i < timeArray.length; i++) {
            chronometer.start();
            createMazeRunnable.run();
            chronometer.stop();
            timeArray[i] = chronometer.getMilliseconds();
        }
        int totalTime = 0;
        for (int i = 0; i < timeArray.length; i++) {
        	System.out.println((i+1)+" generate time = "+timeArray[i]);
        	totalTime += timeArray[i];
        }
        System.out.println("total time = " + totalTime);
        System.out.println("average time = " + (totalTime / (float) timeArray.length));
    }

    private void recursiveDivision() {
    	calculate(new Runnable() {
			@Override
			public void run() {
				new RecursiveDivision(SIZE, SIZE).makeMaze();				
			}
    	});
    }

    private void ellers() {
    	calculate(new Runnable() {
			@Override
			public void run() {
				new Ellers(SIZE, SIZE).makeMaze();				
			}
    	});
    }

    private void growingTree() {
    	calculate(new Runnable() {
			@Override
			public void run() {
				new GrowingTree(SIZE, SIZE).makeMaze();				
			}
    	});
    }

    private void kruskal() {
    	calculate(new Runnable() {
			@Override
			public void run() {
				new Kruskal(SIZE, SIZE);				
			}
    	});
    }

    private void prims() {
    	calculate(new Runnable() {
			@Override
			public void run() {
				new Prim(SIZE, SIZE);				
			}
    	});
    }
    private void binaryTree() {
    	calculate(new Runnable() {
			@Override
			public void run() {
				new BinaryTree(SIZE, SIZE);				
			}
    	});
    }
    private void backTracker() {
    	calculate(new Runnable() {
			@Override
			public void run() {
				new BackTracker(SIZE, SIZE).makeMaze();				
			}
    	});
    }

}
