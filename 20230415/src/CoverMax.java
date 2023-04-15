import java.util.Arrays;
import java.util.PriorityQueue;

public class CoverMax {
    static class Line {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static int maxCover(int[][] m) {
        Line[] lines=new Line[m.length];
        for (int i = 0; i < lines.length; i++) {
            lines[i]=new Line(m[i][0],m[i][1]);
        }
        Arrays.sort(lines,(a,b)->a.start-b.start);
        PriorityQueue<Integer> heap=new PriorityQueue<>();
        int max=0;
        for (int i = 0; i < lines.length; i++) {
            while (!heap.isEmpty()&&lines[i].start>=heap.peek()) {
                heap.poll();
            }
            heap.add(lines[i].end);
            max=Math.max(max, heap.size());
        }
        return max;
    }
}
