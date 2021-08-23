package interview.models

interface CopterIF {
    List<Cell> explore();
    void launch(CurrPosition launchPosition);
    void reset()
}