package com.company;
/*
Трехмерный класс точки
*/
public class Point3d {
    /*Координата X*/
    private double xCoord;
    /*Координата Y*/
    private double yCoord;
    /*Координата Z*/
    private double zCoord;
    /*Конструктор иницализации*/
    public Point3d(double x, double y, double z){
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }
    /*Конструктор по умолчанию*/
    public Point3d(){
        //Вызов конструктора с тремя параметрами
        this(0.0, 0.0, 0.0);
    }
    /*Возвращение значения X*/
    public double getX(){
        return xCoord;
    }
    /*Возвращение значения Y*/
    public double getY(){
        return yCoord;
    }
    /*Возвращение значения Z*/
    public double getZ(){
        return zCoord;
    }
    /*Установка координаты X*/
    public void setX(double val){
        xCoord = val;
    }
    /*Установка координаты Y*/
    public void setY(double val){
        yCoord = val;
    }
    /*Установка координаты Z*/
    public void setZ(double val){
        zCoord = val;
    }
    /*Сравнение двух точек по координатам*/
    public boolean isEqual(Point3d point2){
        return (xCoord == point2.xCoord & yCoord == point2.yCoord & zCoord == point2.zCoord);
    }
    /*Нахождение расстояния между двумя точками*/
    public double distanceTo(Point3d point2){
        return Math.round((Math.sqrt(Math.pow(xCoord - point2.xCoord, 2)
                + Math.pow(yCoord - point2.yCoord, 2) +
                Math.pow(zCoord - point2.zCoord, 2))) * 100 / 100);
    }
}
