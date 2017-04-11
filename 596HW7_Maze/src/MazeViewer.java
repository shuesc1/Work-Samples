//package maze;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 *  Visualization panel for the maze.  Contains the 
 *  driver for calling various maze solving and maze
 *  formation algorithms.
 */
public class MazeViewer extends JPanel {

    private Maze maze;
    private MazeCell currentCell;

    private final static int pad = 50;
    private final static int size = 25;
    private final static BasicStroke stroke = new BasicStroke(2.0f);

    /**
     *  Main driver function for the program.  Calls various
     *  maze solving and maze formation algorithms, depending 
     *  on the input flags.
     *
     *  @param args  The arguments sent to the method via the
     *               console.  
     */
    public static void main(String args[]) {
        System.out.println("Welcome to the maze generation program");
        JFrame  frame     = new JFrame("Maze");
        MazeViewer viewer = new MazeViewer();
        frame.getContentPane().add(viewer);
        viewer.init(args);
        frame.setSize(viewer.getSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        viewer.viewMaze(args[0]);
    }

    /**
     *  Initializes the MazeViewer by initializing the
     *  maze according to the input arguments.  Checks
     *  that the arguments correspond to expected values.
     *  Parses the maze from the input filename.
     *
     *  @param args  Arguments sent from the console.
     */
    public void init(String[] args) {
        if (!checkArgs(args)) {
            System.out.println("The single argument needs to random or bfs");
            System.exit(1);
        }

        int size = Integer.parseInt(args[1]);
        maze = new Maze(size, size);

        maze.setViewer(this);

        setSize(maze.getCols()*size+2*pad, maze.getRows()*size+2*pad);

        setBackground(Color.white);
        setForeground(Color.black);
    }


    /**
     *  Generates the maze using Kruskal's algorithm and then solves it
     *  @param solve    
     *  
     *  */
    public void viewMaze(String solve) {
        maze.generateMaze();
        maze.solveMaze(solve);
    }

    /**
     *  Repaints the panel with the maze.  Sets the current cell
     *  to the parameter; the current cell will be colored.  
     *  Then waits, so the user can observe the visualization.
     *  @param cell  The current cell, to be colored.
     *  @param pauseTime The number of milliseconds to pause
     */
    public synchronized void visualize(MazeCell cell , int pauseTime) {
        currentCell = cell;
        repaint();      
        try {
            wait(pauseTime);
        }
        catch (Exception e) {}
    }
    
    /***
     *  Repaints the panel with the maze.  Sets the current cell
     *  to the parameter; the current cell will be colored.  
     *  Then waits, so the user can observe the visualization.
     * @param cell
     */
    public synchronized void visualize(MazeCell cell) {
        visualize(cell, 250);
    }

    /**
     *  Paints the maze.  This method is called whenever <code>repaint()</code> is
     *  called.  For each cell in the maze, the walls of the cell that are up are 
     *  drawn.  Then if the cell is the "start" cell for exploring the maze, a green
     *  square is drawn within the cell; if it is the "end" cell, a red square is
     *  drawn within the cell; and if it is the "current" cell the algorithm is 
     *  looking at, a blue square is drawn within the cell.  Finally, all cells that
     *  have been "visited" are colored yellow.
     *
     *  @param g  Graphics object on which to draw the maze.
     */
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D)g;
        // Make the stroke a thick black line
        g2D.setStroke(stroke);
        for (int j=0; j<maze.getCols(); j++) {
            for (int i=0; i<maze.getRows(); i++) {
                // Draw lines from the x-coordinate to the y-coordinate.
                // On the jth column, the x-coordinate is 
                // j times the size of the cell plus the padding.
                // On the ith row, the y-coordinate is 
                // i times the size of the cell plus the padding.
                int x = j*size+pad;
                int y = i*size+pad;
                MazeCell cell = maze.getCell(i, j);

                // Paint the visited cells yellow
                if (cell.visited()) {
                    g2D.setColor(Color.yellow);
                    g2D.fill(new Rectangle2D.Double(x+5, y+5, size-10, size-10));
                    g2D.setColor(Color.black);
                }
                // Paint the examined cells gray
                if (cell.examined()) {
                    g2D.setColor(Color.gray);
                    g2D.fill(new Rectangle2D.Double(x+5, y+5, size-10, size-10));
                    g2D.setColor(Color.black);
                }
                // Draw a blue rectangle within the current cell.
                if (cell == currentCell) {
                    g2D.setColor(Color.blue);
                    g2D.fill(new Rectangle2D.Double(x+5, y+5, size-10, size-10));
                    g2D.setColor(Color.black);
                }
                // Paint the start cell green
                if (cell == maze.getStartCell()) {
                    g2D.setColor(Color.green);
                    g2D.fill(new Rectangle2D.Double(x+5, y+5, size/2, size/2));
                    g2D.setColor(Color.black);
                }
                // Paint the end cell red
                if (cell == maze.getEndCell()) {
                    g2D.setColor(Color.red);
                    g2D.fill(new Rectangle2D.Double(x+5, y+5, size/2, size/2));
                    g2D.setColor(Color.black);
                }

                // Draw the walls of this cell that are up.
                if (cell.north())
                    g2D.draw(new Line2D.Double(x, y, x+size, y));
                if (cell.south())
                    g2D.draw(new Line2D.Double(x, y+size, x+size, y+size));
                if (cell.east())
                    g2D.draw(new Line2D.Double(x+size, y, x+size, y+size));
                if (cell.west())
                    g2D.draw(new Line2D.Double(x, y, x, y+size));
            }
        }
    }

    /**
     *  We expect the maze solving argument to come from a finite set
     *  @param args  one of "dfs", "bfs" or "random"
     */
    private boolean checkArgs(String[] args) {
        if (!(args[0].equals("dfs") || 
                args[0].equals("bfs") || 
                args[0].equals("random"))) return false;
        return true;
    }

    
}



