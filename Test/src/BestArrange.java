import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int bestArrange(Program[] programs) {
        Arrays.sort(programs, new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.end-o2.end;
            }
        });
        int timeLine=0;
        int result=0;
        // 依次遍历每一个会议，结束时间早的会议优先遍历
        for (int i = 0; i < programs.length; i++) {
            if(timeLine<=programs[i].start) {
                result++;
                timeLine=programs[i].end;
            }
        }
        return result;
    }
}
