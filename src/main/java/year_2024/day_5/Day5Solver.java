package year_2024.day_5;

import java.util.ArrayList;
import java.util.List;

public class Day5Solver {

    private List<PageOrderingRule> pageOrderingRules;
    private List<UpdateOrder> updateOrders;

    public Day5Solver(List<String> puzzleInput) {
        initializeData(puzzleInput);
    }

    public int solvePart1() {
        return this.updateOrders.stream()
                .filter(updateOrder -> updateOrder.isValid(this.pageOrderingRules))
                .mapToInt(UpdateOrder::getMiddlePage)
                .sum();
    }

    public int solvePart2() {
        var invalidOrders = this.updateOrders.stream()
                .filter(order -> !order.isValid(this.pageOrderingRules))
                .toList();

        invalidOrders.forEach(this::repairInvalidOrder);

        return invalidOrders.stream()
                .mapToInt(UpdateOrder::getMiddlePage)
                .sum();
    }

    private void repairInvalidOrder(UpdateOrder order) {
        while (!order.isValid(this.getPageOrderingRules())) {
            for (var rule : this.pageOrderingRules) {
                int previous = rule.previous();
                int next = rule.next();
                if (order.contains(previous) && order.contains(next)) {
                    int indexOfPrev = order.indexOf(previous);
                    int indexOfNext = order.indexOf(next);
                    if (indexOfPrev > indexOfNext) {
                        order.pages()[indexOfNext] = previous;
                        order.pages()[indexOfPrev] = next;
                    }
                }
            }
        }
    }

    public List<PageOrderingRule> getPageOrderingRules() {
        return pageOrderingRules;
    }

    private void initializeData(List<String> puzzleInput) {
        this.pageOrderingRules = new ArrayList<>();
        this.updateOrders = new ArrayList<>();
        boolean isFirtPartOfInput = true;
        for (String line : puzzleInput) {
            try {
                if (isFirtPartOfInput) {
                    this.pageOrderingRules.add(PageOrderingRule.parse(line));
                } else {
                    this.updateOrders.add(UpdateOrder.parse(line));
                }
            } catch (NumberFormatException e) {
                System.out.println("Cannot parse for line: " + line);
            }
            if (line.isEmpty()) {
                isFirtPartOfInput = false;
            }
        }
    }
}
