package year_2025.day_11;

import common.Solver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day11Solver implements Solver<Long, Long> {

    private final Map<String, String[]> connectionsMap;
    private final static String START_DEVICE = "you";
    private final static String TARGET_DEVICE = "out";
    private final Map<String, Map<String, Long>> cache;

    public Day11Solver(List<String> puzzleInput) {

        this.connectionsMap = puzzleInput.stream()
                .map(Connection::parseConnection)
                .collect(Collectors.toMap(Connection::input, Connection::outputs));
        this.cache = new HashMap<>();
    }

    @Override
    public Long part1() {
        return getPathsCount(START_DEVICE, TARGET_DEVICE);
    }


    private long getPathsCount(String currentDevice,
                               String target) {

        long fromCache = getFromCache(currentDevice, target);

        if (fromCache != -1) {
            return fromCache;
        }

        if (currentDevice.equals(target)) {
            return 1;
        }
        long cnt = 0;

        String[] outputs = connectionsMap.get(currentDevice);
        if (outputs != null) {
            for (String output : outputs) {
                cnt += getPathsCount(output, target);
                updateCache(currentDevice, target, cnt);
            }
        }
        return cnt;
    }

    private void updateCache(String start,
                             String target,
                             long value) {

        cache.putIfAbsent(start, new HashMap<>());
        cache.get(start).put(target, value);
    }

    private long getFromCache(String start,
                              String target) {
        return cache
                .getOrDefault(start, new HashMap<>())
                .getOrDefault(target, -1L);
    }

    @Override
    public Long part2() {
        //svr-dac
        long svrDacCount = getPathsCount("svr", "dac");
        //svr-fft
        long svrFftCount = getPathsCount("svr", "fft");
        //dac-fft
        long dacFftCount = getPathsCount("dac", "fft");
        //fft-dac
        long fftDacCount = getPathsCount("fft", "dac");
        //dac-out
        long dacOutCount = getPathsCount("dac", "out");
        //fft-out
        long fftOutCount = getPathsCount("fft", "out");

        //svr-fft-dac-out
        long svr_fft_dac_out_paths = svrFftCount * fftDacCount * dacOutCount;
        //svr-dac-fft-out
        long svr_dac_fft_out_paths = svrDacCount * dacFftCount * fftOutCount;

        return svr_dac_fft_out_paths + svr_fft_dac_out_paths;
    }


}
