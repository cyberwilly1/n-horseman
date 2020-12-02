import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Horses {
    private int num_horses;
    private int[] solution;
    private List<int[]> solutionList;
    private static int num_Solutions;

    public Horses(int num_horses) {
        this.num_horses = num_horses;
        solution = new int[num_horses];
        num_Solutions = 0;
        solutionList = new LinkedList<>();
        init();
    }

    public void init() {
        num_Solutions = 0;
        solutionList.clear();
        for (int i = 0; i < solution.length; i++)
            solution[i] = -1;
    }

    public List<int[]> getSolutionList() {
        return solutionList;
    }

    public int getNumberOfSolutions() {
        return num_Solutions;
    }

    public void searchSolution() {
        init();
        backTracking(solution, 0);
    }

    public boolean backTracking(int[] solution, int horse) {
        boolean succes = false;
        if (horse < num_horses) {
            do {
                solution[horse]++;
                boolean valid = horseIsValid(solution, horse);
                System.out.println(Arrays.toString(solution) + " " + (valid ? "Sol Parcial" : "")
                        + (valid && (horse == num_horses - 1) ? " -> SOLUCION" : ""));
                if (valid) {
                    succes = backTracking(solution, horse + 1);
                    if (horse == num_horses - 1) {
                        num_Solutions++;
                        solutionList.add(solution.clone());
                    }
                }
            } while (solution[horse] < num_horses - 1 && !succes);
            solution[horse] = -1;
        }
        return succes;
    }

    public boolean horseIsValid(int[] solution, int horse) {
        boolean ok = true;
        if (horse - 1 >= 0) {
            if (solution[horse] == solution[horse - 1] - 2 || solution[horse] == solution[horse - 1] + 2)
                ok = false;
        }
        if (horse - 2 >= 0) {
            if (solution[horse] == solution[horse - 2] - 1 || solution[horse] == solution[horse - 2] + 1)
                ok = false;
        }
        return ok;
    }

    public static void main(String[] args) {
        Horses horses = new Horses(2);
        horses.searchSolution();
        System.out.println("resultados -> " + num_Solutions);
    }
}