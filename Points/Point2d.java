package Points;

/**
* Точка в двумерном пространстве.
**/
public class Point2d {
/** Координата X точки **/
private double xCoord;
/** координата Y точки **/
private double yCoord;
/** Конструктор инициализирующий начальное значение координат точки (x,
y). **/
public Point2d(double x, double y) {
xCoord = x;
yCoord = y;
}
/** конструктор без аргументов: по умолчанию точка ставится в начало
координат. **/
public Point2d() {
// Вызываем конструктор с двумя аргументами для инициализации позиции.
this(0, 0);
}
/** Возвращает координату X точки. **/
public double getX() {
return xCoord;
}
/** Возвращает координату Y точки. **/
public double getY() {
return yCoord;
}
/** Изменяет координату X точки. **/
public void setX(double val) {
xCoord = val;
}
/** Изменяет координату Y точки. **/
public void setY(double val) {
yCoord = val;
}
}