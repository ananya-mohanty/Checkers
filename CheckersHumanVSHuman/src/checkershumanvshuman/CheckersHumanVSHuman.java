/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkershumanvshuman;

/**
 *
 * @author ananya
 */
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

///**
// *
// * @author ananya
// */
//
class Board extends JPanel implements MouseListener, MouseMotionListener{
    public char[][] matrix;
    public int nRed, nBlack,x,y;
    public int[][] red, black;
    Jframe frame;
    Jbutton b;
     Board(){
     addMouseListener(this);
    addMouseMotionListener(this);
        matrix = new char[8][8];
        nRed=12;
        nBlack=12;
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                matrix[i][j]='-';
                
            }
            
        }
       int i;
       
       for(i=0; i<8; i+=2){
//           red[]
            matrix[i][1]='R';
            matrix[i][5]='B';
            matrix[i][7]='B';
        }
        for(i=1; i<8; i+=2){
            matrix[i][0]='R';
            matrix[i][2]='R';
            matrix[i][6]='B';
        }
        
    }
     
     
     
    void printIt(){
        int i,j;
        for(i=0; i<8; i++){
            
            for(j=0; j<8; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
           
        }
         System.out.println();
            System.out.println();
    }
     public void paint(Graphics g){
         super.paint(g);
         int factor=50;
         Graphics2D g2d = (Graphics2D) g;
 Color brown = new Color(102, 51, 0);
         Color grey = new Color(160,160,160);
         for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                 g.drawRect(i*factor,j*factor,factor,factor);
                 g.setColor(grey);
                 if((i+j)%2!=0)
                 g.fillRect(i*factor,j*factor,factor,factor);
                 
                 if(matrix[i][j]=='R'){
                    
//                    int t=matrix[i][j];
                     Ellipse2D.Double circle2 = new Ellipse2D.Double(i*factor, j*factor, factor, factor);
                     g2d.setColor(Color.RED);
                     g2d.fill(circle2);
                 }
                 if(matrix[i][j]=='B'){
                    
//                    int t=matrix[i][j];
                     Ellipse2D.Double circle2 = new Ellipse2D.Double(i*factor, j*factor, factor, factor);
                     g2d.setColor(Color.BLACK);
                     g2d.fill(circle2);
                 }
                 if(matrix[i][j]=='r'){
                    
//                    int t=matrix[i][j];
                     Ellipse2D.Double circle2 = new Ellipse2D.Double(i*factor, j*factor, factor, factor);
                     g2d.setColor(Color.PINK);
                     g2d.fill(circle2);
                 }
                   if(matrix[i][j]=='b'){
                    
//                    int t=matrix[i][j];
                     Ellipse2D.Double circle2 = new Ellipse2D.Double(i*factor, j*factor, factor, factor);
                     g2d.setColor(brown);
                     g2d.fill(circle2);
                 }
            }
     }
     }
 

    int maxValue(char[][] currBoard, int cutoff, int alpha, int beta){
        if(cutoff>5)
               return getScore(currBoard);
        
        char[][] nextBoard;
        nextBoard= new char[8][8];
         char[][] tempBoard;
        tempBoard= new char[8][8];
         for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                 nextBoard[i][j]=currBoard[i][j];
            }
        }
       
       int v=-99999, temp,flag=0;
        int i2=0, j2=0,i1=0,j1=0;
        
        for(int i=0; i<8;i++){
            for(int j=0; j<8; j++){
                if(nextBoard[i][j]=='B' || nextBoard[i][j]=='b'){
//                        System.out.println(i + " " +j);
                    if(nextBoard[i][j]=='b' && i<5 && j<5 && nextBoard[i+3][j+3]=='-' &&( nextBoard[i+1][j+1]=='R' ||  nextBoard[i+1][j+1]=='r') && ( nextBoard[i+2][j+2]=='R' ||  nextBoard[i+2][j+2]=='r')){
                        tempBoard = action(i, j, i+3, j+3, 1, nextBoard);
                        temp=minValue(tempBoard, cutoff+1, alpha, beta);
                        flag=2;
                        
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i+3;
                            j2=j+3;
                            flag=2;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }
                    if(nextBoard[i][j]=='b' && i>2 && j<5 && nextBoard[i-3][j+3]=='-' &&( nextBoard[i-1][j+1]=='R' ||  nextBoard[i-1][j+1]=='r') &&( nextBoard[i-2][j+2]=='R' ||  nextBoard[i-2][j+2]=='r')){
                        tempBoard = action(i, j, i-3, j+3, 1, nextBoard);
                       temp=minValue(tempBoard, cutoff+1, alpha, beta);
                        
                        flag=2;
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-3;
                            j2=j+3;
                            flag=2;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }
                    if(nextBoard[i][j]=='b' && i>2 && j>2 &&(nextBoard[i-1][j-1]=='R' || nextBoard[i-1][j-1]=='r') && (nextBoard[i-2][j-2]=='R' || nextBoard[i-2][j-2]=='r') && nextBoard[i-3][j-3]=='-' ){
                         tempBoard = action(i, j, i-3, j-3, 1, nextBoard);
                       temp=minValue(tempBoard, cutoff+1, alpha, beta);
                        flag=2;
//                         temp=action(i, j, i-2, j-2, 1);
                         if(temp >= v)
                        {
                            i1=i;
                            j1=j;
                             v=temp;
                             i2=i-3;
                            j2=j-3;
                              flag=1;
//                            nextBoard= getnext(i, j, i-2, j-2, 1);
                        }

                    }
//
                    if(nextBoard[i][j]=='b' &&i<5 && j>2 && (nextBoard[i+1][j-1]=='R' || nextBoard[i+1][j-1]=='r') &&(nextBoard[i+2][j-2]=='R' || nextBoard[i+2][j-2]=='r') && nextBoard[i+3][j-3]=='-' ){
                        flag=2;
                          tempBoard = action(i, j, i+3, j-3, 1, nextBoard);
                        
                       temp=minValue(tempBoard, cutoff+1, alpha, beta);
                        
                         if(temp >= v)
                        {
                            i1=i;
                            j1=j;
                             v=temp;
                             i2=i+3;
                             j2=j-3;
//                              flag=1;
//                            nextBoard= getnext(i, j, i-1, j-1, 1);
                        }
//                            nextBoard=action(i,j, i+2, j-2, 1);
                    }
                    if(flag!=2 && nextBoard[i][j]=='b' && i<6 && j<6 && nextBoard[i+2][j+2]=='-' &&( nextBoard[i+1][j+1]=='R' ||  nextBoard[i+1][j+1]=='r')){
                        tempBoard = action(i, j, i+2, j+2, 1, nextBoard);
                        temp=minValue(tempBoard, cutoff+1, alpha, beta);
                       flag=1;
                        alpha=Math.max(alpha, temp);
//                        if(alpha>beta)
//                            break;
                        
                        
                        
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i+2;
                            j2=j+2;
                            flag=1;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }
                     if(flag!=2&& nextBoard[i][j]=='b' && i>1 && j<6 && nextBoard[i-2][j+2]=='-' &&( nextBoard[i-1][j+1]=='R' ||  nextBoard[i-1][j+1]=='r')){
                        tempBoard = action(i, j, i-2, j+2, 1, nextBoard);
                       flag=1;
                        temp=minValue(tempBoard, cutoff+1, alpha, beta);
                       
                        alpha=Math.max(alpha, temp);
//                        if(alpha>beta)
//                            break;
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-2;
                            j2=j+2;
                            flag=1;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }
                      if(flag!=2 && i>1 && j>1 &&(nextBoard[i-1][j-1]=='R' || nextBoard[i-1][j-1]=='r') && nextBoard[i-2][j-2]=='-' ){
                         tempBoard = action(i, j, i-2, j-2, 1, nextBoard);
                      temp=minValue(tempBoard, cutoff+1, alpha, beta);
                       flag=1;
                        alpha=Math.max(alpha, temp);
//                        if(alpha>beta)
//                            break;
                         if(temp >= v)
                        {
                            i1=i;
                            j1=j;
                             v=temp;
                             i2=i-2;
                            j2=j-2;
                              flag=1;
                        }
//                         
                    }
//
                    if(flag!=2 && i<6 && j>1 && (nextBoard[i+1][j-1]=='R' || nextBoard[i+1][j-1]=='r') && nextBoard[i+2][j-2]=='-' ){
//                         temp=action(i, j, i+2, j-2, 1);
                          tempBoard = action(i, j, i+2, j-2, 1, nextBoard);
                     temp=minValue(tempBoard, cutoff+1, alpha, beta);
                       flag=1;
                        alpha=Math.max(alpha, temp);
//                        if(alpha>beta)
//                            break;
                         if(temp >= v)
                        {
                            i1=i;
                            j1=j;
                             v=temp;
                             i2=i+2;
                            j2=j-2;
                              flag=1;
//                            nextBoard= getnext(i, j, i-1, j-1, 1);
                        }
//                            nextBoard=action(i,j, i+2, j-2, 1);
                    }

                    
                    
                    if(flag==0 && nextBoard[i][j]=='b' && i!=0 && j!=7 && nextBoard[i-1][j+1]=='-'){
                        tempBoard = action(i, j, i-1, j+1, 1, nextBoard);
                        temp=minValue(tempBoard, cutoff+1, alpha, beta);
                       
                        alpha=Math.max(alpha, temp);
//                        if(alpha>beta)
//                            break;
                        
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-1;
                            j2=j+1;
                            
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }
                    if(flag==0 && i!=7  && j!=0 && nextBoard[i+1][j-1]=='-'){
                        tempBoard = action(i, j, i+1, j-1, 1, nextBoard);
                       temp=minValue(tempBoard, cutoff+1, alpha, beta);
                       
                        alpha=Math.max(alpha, temp);
//                        if(alpha>beta)
//                            break;
                        
                        if(temp >= v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i+1;
                            j2=j-1;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                    }
                            
                    if(flag==0 && i!=0  && j!=0 && nextBoard[i-1][j-1]=='-'){
                         tempBoard = action(i, j, i-1, j-1, 1, nextBoard);
                        temp=minValue(tempBoard, cutoff+1, alpha, beta);
                       
                        alpha=Math.max(alpha, temp);
//                        if(alpha>beta)
//                            break;
//                         temp=action(i, j, i-1, j-1, 1);
                         if(temp >= v)
                        {
                            i1=i;
                            j1=j;
                             v=temp;
                             i2=i-1;
                            j2=j-1;
//                            nextBoard= getnext(i, j, i-1, j-1, 1);
                        }
                    }
                     if(flag==0 && nextBoard[i][j]=='b' && i!=7 && j!=7 && nextBoard[i+1][j+1]=='-'){
                        tempBoard = action(i, j, i+1, j+1, 1, nextBoard);
                       temp=minValue(tempBoard, cutoff+1, alpha, beta);
                       
                        alpha=Math.max(alpha, temp);
//                        if(alpha>beta)
//                            break;
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i+1;
                            j2=j+1;
//                          
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }     
                   
                    
                   
                }
            }
        }
      
      
    return v;   
    }
//    
    int minValue(char[][] currBoard, int cutoff, int alpha, int beta){
        
        if(cutoff>6)
               return getScore(currBoard);
             char[][] nextBoard;
        nextBoard= new char[8][8];
         char[][] tempBoard;
//         int va
        tempBoard= new char[8][8];
         for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                 nextBoard[i][j]=currBoard[i][j];
            }
        }
       int v=99999, temp, flag=0;
        int i2=0, j2=0,i1=0,j1=0;
        
        for(int i=0; i<8;i++){
            for(int j=0; j<8; j++){
                if(nextBoard[i][j]=='R'|| nextBoard[i][j]=='r'){
//                  
                       if(nextBoard[i][j]=='r' && i>2 && j>2 && nextBoard[i-3][j-3]=='-' && (nextBoard[i-1][j-1]=='B'|| nextBoard[i-1][j-1]=='b') &&(nextBoard[i-2][j-2]=='B'|| nextBoard[i-2][j-2]=='b')){
                            tempBoard = action(i, j, i-3, j-3, 2, nextBoard);
                             temp=maxValue(tempBoard, cutoff+1, alpha, beta);
                              beta=Math.min(alpha, temp);
                              flag=2;
//                        temp = action(i, j, i+1, j+1, 2);
                        if(temp < v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-3;
                            j2=j-3;
                            
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        }
                        if(  nextBoard[i][j]=='r' && i<5 && j>2 && nextBoard[i+3][j-3]=='-' && (nextBoard[i+1][j-1]=='B'|| nextBoard[i+1][j-1]=='b') && (nextBoard[i+2][j-2]=='B'|| nextBoard[i+2][j-2]=='b')){
                            tempBoard = action(i, j, i+3, j-3, 2, nextBoard);
                              temp=maxValue(tempBoard, cutoff+1, alpha, beta);
                              beta=Math.min(alpha, temp);
                             flag=2;
//                        temp = action(i, j, i+1, j+1, 2);
                            if(temp < v)
                            {
    //                            temp=v;
                                v=temp;
                                i1=i;
                                j1=j;
                                i2=i+3;
                                j2=j-3;
                               
                            }
                        }
                       if( nextBoard[i][j]=='r' && j<5   && i>2 && (nextBoard[i-1][j+1]=='B'|| nextBoard[i-1][j+1]=='b') &&  (nextBoard[i-2][j+2]=='B'|| nextBoard[i-2][j+2]=='b') && nextBoard[i-3][j+3]=='-'){
                              tempBoard = action(i, j, i-3, j+3, 2, nextBoard);
                                 temp=maxValue(tempBoard, cutoff+1, alpha, beta);
                              beta=Math.min(alpha, temp);
                              flag=2;
//                                temp = action(i,j, i-2, j+2, 2);
                        if(temp < v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-3;
                            j2=j+3;
                           
                        }
                    }
                        if(nextBoard[i][j]=='r' && j<5   && i<5 && (nextBoard[i+1][j+1]=='B'|| nextBoard[i+1][j+1]=='b') &&(nextBoard[i+2][j+2]=='B'|| nextBoard[i+2][j+2]=='b') && nextBoard[i+3][j+3]=='-'){
//                                temp = action(i,j, i+2, j+2, 2);
                                  tempBoard = action(i, j, i+3, j+3, 2, nextBoard);
                                temp=maxValue(tempBoard, cutoff+1, alpha, beta);
                              beta=Math.min(alpha, temp);
                              flag=2;
                        if(temp <v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i+3;
                            j2=j+3;
                            
                        }
                    }
                        
                        if(flag!=2 && nextBoard[i][j]=='r' && i>1 && j>1 && nextBoard[i-2][j-2]=='-' && (nextBoard[i-1][j-1]=='B'|| nextBoard[i-1][j-1]=='b')){
                            tempBoard = action(i, j, i-2, j-2, 2, nextBoard);
                             temp=maxValue(tempBoard, cutoff+1, alpha, beta);
                                flag=1;
                        beta=Math.min(beta, temp);
//                        if(alpha>beta)
//                            break;
//                        temp = action(i, j, i+1, j+1, 2);
                        if(temp < v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-2;
                            j2=j-2;
                            flag=1;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        }
                        if(flag!=2 && nextBoard[i][j]=='r' && i<6 && j>1 && nextBoard[i+2][j-2]=='-' && (nextBoard[i+1][j-1]=='B'|| nextBoard[i+1][j-1]=='b')){
                            tempBoard = action(i, j, i+2, j-2, 2, nextBoard);
                              temp=maxValue(tempBoard, cutoff+1, alpha, beta);
                       flag=1;
                        beta=Math.min(beta, temp);
//                        if(alpha>beta)
//                            break;
//                        temp = action(i, j, i+1, j+1, 2);
                            if(temp < v)
                            {
    //                            temp=v;
                                v=temp;
                                i1=i;
                                j1=j;
                                i2=i+2;
                                j2=j-2;
                                flag=1;
    //                            nextBoard= getnext(i, j, i+1, j-1, 1);
                            }
                        }
                       
                        if(flag!=2 && j<6 && i>1 && (nextBoard[i-1][j+1]=='B'|| nextBoard[i-1][j+1]=='b') && nextBoard[i-2][j+2]=='-'){
                              tempBoard = action(i, j, i-2, j+2, 2, nextBoard);
                       temp=maxValue(tempBoard, cutoff+1, alpha, beta);
                       flag=1;
                        beta=Math.min(beta, temp);
//                        if(alpha>beta)
//                            break;
//                                temp = action(i,j, i-2, j+2, 2);
                        if(temp < v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-2;
                            j2=j+2;
                            flag=1;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                    }
                        if(flag!=2 && j<6   && i<6 && (nextBoard[i+1][j+1]=='B'|| nextBoard[i+1][j+1]=='b') && nextBoard[i+2][j+2]=='-'){
//                                temp = action(i,j, i+2, j+2, 2);
                                  tempBoard = action(i, j, i+2, j+2, 2, nextBoard);
                        temp=maxValue(tempBoard, cutoff+1, alpha, beta);
                       flag=1;
                        beta=Math.min(beta, temp);
//                        if(alpha>beta)
//                            break;
                        if(temp <v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i+2;
                            j2=j+2;
                            flag=1;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                    }
                        if(flag==0 && i!=7  && j!=7 && nextBoard[i+1][j+1]=='-'){
                            tempBoard = action(i, j, i+1, j+1, 2, nextBoard);
                          temp=maxValue(tempBoard, cutoff+1, alpha, beta);
                       
                        beta=Math.min(beta, temp);
//                        if(alpha>beta)
//                            break;
                        if(temp < v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i+1;
                            j2=j+1;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                    }
                        if(flag==0 &&  i!=0  && j!=7 && nextBoard[i-1][j+1]=='-'){
                              tempBoard = action(i, j, i-1, j+1, 2, nextBoard);
                        temp=maxValue(tempBoard, cutoff+1, alpha, beta);
                       
                        beta=Math.min(beta, temp);
//                        if(alpha>beta)
//                            break;
                        if(temp < v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-1;
                            j2=j+1;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                    }
                         if(flag==0 &&   nextBoard[i][j]=='r' && i!=0 && j!=0 && nextBoard[i-1][j-1]=='-'){
                            tempBoard = action(i, j, i-1, j-1, 2, nextBoard);
                              temp=maxValue(tempBoard, cutoff+1, alpha, beta);
                       
                        beta=Math.min(beta, temp);
//                        if(alpha>beta)
//                            break;
                                if(temp < v)
                                {
        //                            temp=v;
                                    v=temp;
                                    i1=i;
                                    j1=j;
                                    i2=i-1;
                                    j2=j-1;
        //                            nextBoard= getnext(i, j, i+1, j-1, 1);
                                }
                        }
                         if(flag==0 && nextBoard[i][j]=='r' && i!=7 && j!=0 && nextBoard[i+1][j-1]=='-'){
                            tempBoard = action(i, j, i+1, j-1, 2, nextBoard);
                             temp=maxValue(tempBoard, cutoff+1, alpha, beta);
                       
                        beta=Math.min(beta, temp);
//                        if(alpha>beta)
//                            break;
                            if(temp < v)
                            {
    //                            temp=v;
                                v=temp;
                                i1=i;
                                j1=j;
                                i2=i+1;
                                j2=j-1;
    //                            nextBoard= getnext(i, j, i+1, j-1, 1);
                            }
                        }
                       
                         
                        
//                  

                }
            }
        }
        
        
        return v;
    
        
    }
 char[][] action(int i1,int  j1,int  i2, int j2, int player, char[][] currMatrix){
        char[][] tempBoard, nextBoard;
//        tempBoard = new char[8][8];
       tempBoard = new char[8][8];
          for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                 tempBoard[i][j]=currMatrix[i][j];
            }
        }
//        nextBoard=matrix;
         int i,j;

            int nr=nRed, nb=nBlack;
        if(Math.abs(i1-i2)==2){
            if(tempBoard[i1][j1]=='B'){
                nr--;
               
                tempBoard[i2][j2]='B';
                
                if(tempBoard[i1][j1]=='b' || j2==0 || j1==0){

                    tempBoard[i2][j2]='b';
                }
                 tempBoard[(i1+i2)/2][(j1+j2)/2]='-';
                tempBoard[i1][j1]='-';
                     
            }
            else if(tempBoard[i1][j1]=='R'){
                nb--;
                tempBoard[(i1+i2)/2][(j1+j2)/2]='-';
                tempBoard[i1][j1]='-';
                tempBoard[i2][j2]='R';
            }
            else if(tempBoard[i1][j1]=='b'){
                nr--;
                tempBoard[(i1+i2)/2][(j1+j2)/2]='-';
                tempBoard[i1][j1]='-';
                tempBoard[i2][j2]='b';
            }
            else if(tempBoard[i1][j1]=='r'){
                nb--;
                tempBoard[(i1+i2)/2][(j1+j2)/2]='-';
                tempBoard[i1][j1]='-';
                tempBoard[i2][j2]='r';
            }
        }
        else{
            if(tempBoard[i1][j1]=='B'){
                tempBoard[i1][j1]='-';
                tempBoard[i2][j2]='B';
                
            }
            else if( tempBoard[i1][j1]=='R'){
                tempBoard[i1][j1]='-';
                tempBoard[i2][j2]='R';
            }
            else if(tempBoard[i1][j1]=='b'){
                tempBoard[i1][j1]='-';
                tempBoard[i2][j2]='b';
            }
            else if(tempBoard[i1][j1]=='r'){
                tempBoard[i1][j1]='-';
                tempBoard[i2][j2]='r';
            }
            
        }

        return tempBoard;
       
    }
 int getScore( char[][] tempBoard){
     
    
     int num=0;
     for(int i=0; i<8; i++){
         for(int j=0; j<8; j++){
             if(tempBoard[i][j]=='R'|| tempBoard[i][j]=='r'){
                 num--;
                 num-=Math.abs(3-j)/2;
             }
             else if(tempBoard[i][j]=='B' || tempBoard[i][j]=='b'){
                 num++;
                  num+=Math.abs(3-j)/2;
             }
         }
     }
     
     return  num;
 }
   
 int anotherkillfor1(int i, int j, char[][]nextBoard){
     char[][]tempBoard;
     int f=0, i1=0, j1=0, i2=0, j2=0, temp, v=-99999, flag=0;
     if(nextBoard[i][j]=='B' || nextBoard[i][j]=='b'){
//                        System.out.println(i + " " +j);
                    if(nextBoard[i][j]=='b' && i<5 && j<5 && nextBoard[i+3][j+3]=='-' &&( nextBoard[i+1][j+1]=='R' ||  nextBoard[i+1][j+1]=='r') && ( nextBoard[i+2][j+2]=='R' ||  nextBoard[i+2][j+2]=='r')){
                        tempBoard = action(i, j, i+3, j+3, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                        temp=minValue(tempBoard, 0, alpha, beta);
                        flag=2;
                        
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i+3;
                            j2=j+3;
                            flag=2;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }
                    if(nextBoard[i][j]=='b' && i>2 && j<5 && nextBoard[i-3][j+3]=='-' &&( nextBoard[i-1][j+1]=='R' ||  nextBoard[i-1][j+1]=='r') &&( nextBoard[i-2][j+2]=='R' ||  nextBoard[i-2][j+2]=='r')){
                        tempBoard = action(i, j, i-3, j+3, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                        temp=minValue(tempBoard, 0,alpha, beta);
                        
                        flag=2;
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-3;
                            j2=j+3;
                            flag=2;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }
                    if(nextBoard[i][j]=='b' && i>2 && j>2 &&(nextBoard[i-1][j-1]=='R' || nextBoard[i-1][j-1]=='r') && (nextBoard[i-2][j-2]=='R' || nextBoard[i-2][j-2]=='r') && nextBoard[i-3][j-3]=='-' ){
                         tempBoard = action(i, j, i-3, j-3, 1, nextBoard);
                       int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                         temp=minValue(tempBoard, 0,alpha, beta);
                        flag=2;
//                         temp=action(i, j, i-2, j-2, 1);
                         if(temp >= v)
                        {
                            i1=i;
                            j1=j;
                             v=temp;
                             i2=i-3;
                            j2=j-3;
                              flag=1;
//                            nextBoard= getnext(i, j, i-2, j-2, 1);
                        }

                    }
//
                    if(nextBoard[i][j]=='b' &&i<5 && j>2 && (nextBoard[i+1][j-1]=='R' || nextBoard[i+1][j-1]=='r') &&(nextBoard[i+2][j-2]=='R' || nextBoard[i+2][j-2]=='r') && nextBoard[i+3][j-3]=='-' ){
                        flag=2;
                          tempBoard = action(i, j, i+3, j-3, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                        temp=minValue(tempBoard, 0,alpha, beta);
                        
                         if(temp >= v)
                        {
                            i1=i;
                            j1=j;
                             v=temp;
                             i2=i+3;
                             j2=j-3;
//                              flag=1;
//                            nextBoard= getnext(i, j, i-1, j-1, 1);
                        }
//                            nextBoard=action(i,j, i+2, j-2, 1);
                    }
                    if(flag!=2 && nextBoard[i][j]=='b' && i<6 && j<6 && nextBoard[i+2][j+2]=='-' &&( nextBoard[i+1][j+1]=='R' ||  nextBoard[i+1][j+1]=='r')){
                        tempBoard = action(i, j, i+2, j+2, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                        temp=minValue(tempBoard, 0, alpha, beta);
                        flag=1;
                        
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i+2;
                            j2=j+2;
                            flag=1;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }
                     if(flag!=2 && nextBoard[i][j]=='b' && i>1 && j<6 && nextBoard[i-2][j+2]=='-' &&( nextBoard[i-1][j+1]=='R' ||  nextBoard[i-1][j+1]=='r')){
                        tempBoard = action(i, j, i-2, j+2, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                        temp=minValue(tempBoard, 0,alpha, beta);
                        
                        flag=1;
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-2;
                            j2=j+2;
                            flag=1;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }
                      if(flag!=2 && i>1 && j>1 &&(nextBoard[i-1][j-1]=='R' || nextBoard[i-1][j-1]=='r') && nextBoard[i-2][j-2]=='-' ){
                         tempBoard = action(i, j, i-2, j-2, 1, nextBoard);
                       int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                         temp=minValue(tempBoard, 0,alpha, beta);
                        flag=1;
//                         temp=action(i, j, i-2, j-2, 1);
                         if(temp >= v)
                        {
                            i1=i;
                            j1=j;
                             v=temp;
                             i2=i-2;
                            j2=j-2;
                              flag=1;
//                            nextBoard= getnext(i, j, i-2, j-2, 1);
                        }

                    }
//
                    if(flag!=2 && i<6 && j>1 && (nextBoard[i+1][j-1]=='R' || nextBoard[i+1][j-1]=='r') && nextBoard[i+2][j-2]=='-' ){
                        flag=1;
                          tempBoard = action(i, j, i+2, j-2, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                        temp=minValue(tempBoard, 0,alpha, beta);
                        
                         if(temp >= v)
                        {
                            i1=i;
                            j1=j;
                             v=temp;
                             i2=i+2;
                             j2=j-2;
                              flag=1;
//                            nextBoard= getnext(i, j, i-1, j-1, 1);
                        }
//                            nextBoard=action(i,j, i+2, j-2, 1);
                    }
     }
     
     
     if(flag==0)
           return 0;
     else{
         if(flag==1 || flag==2){
             if(flag==1)
                nRed--;
             else nRed-=2;
             nextBoard=action(i1,j1,i2,j2, 1, nextBoard);
             for(int ii=0; ii<8; ii++){
            for(int jj=0; jj<8; jj++){
                if(jj==0 && nextBoard[ii][jj]=='B')
                        nextBoard[ii][jj]='b';
                if(jj==7 && nextBoard[ii][jj]=='R')
                        nextBoard[ii][jj]='r';
                    matrix[ii][jj]=nextBoard[ii][jj];
                }
                }
             repaint();
             
         }
         return anotherkillfor1(i2, j2, nextBoard);
     }
//     return f;
 }
 int anotherkillfor2(int i, int j, char[][]nextBoard){
     char[][]tempBoard;
     int f=0, i1=0, j1=0, i2=0, j2=0, temp, v=-99999, flag=0;
     
        if(nextBoard[i][j]=='R'|| nextBoard[i][j]=='r'){
//                  
                        if(nextBoard[i][j]=='r' && i>2 && j>2 && nextBoard[i-3][j-3]=='-' && (nextBoard[i-1][j-1]=='B'|| nextBoard[i-1][j-1]=='b') &&(nextBoard[i-2][j-2]=='B'|| nextBoard[i-2][j-2]=='b')){
                            tempBoard = action(i, j, i-3, j-3, 2, nextBoard);
                            int alpha=Integer.MAX_VALUE, beta=Integer.MIN_VALUE;
                              temp=maxValue(tempBoard, 0,alpha, beta);
                              beta=Math.min(alpha, temp);
                              flag=2;
//                        temp = action(i, j, i+1, j+1, 2);
                        if(temp < v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-3;
                            j2=j-3;
                            
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        }
                        if(  nextBoard[i][j]=='r' && i<5 && j>2 && nextBoard[i+3][j-3]=='-' && (nextBoard[i+1][j-1]=='B'|| nextBoard[i+1][j-1]=='b') && (nextBoard[i+2][j-2]=='B'|| nextBoard[i+2][j-2]=='b')){
                            tempBoard = action(i, j, i+3, j-3, 2, nextBoard);
                             int alpha=Integer.MAX_VALUE, beta=Integer.MIN_VALUE;
                             temp=maxValue(tempBoard, 0,alpha, beta);
                              beta=Math.min(alpha, temp);
                             flag=2;
//                        temp = action(i, j, i+1, j+1, 2);
                            if(temp < v)
                            {
    //                            temp=v;
                                v=temp;
                                i1=i;
                                j1=j;
                                i2=i+3;
                                j2=j-3;
                               
                            }
                        }
                       if( nextBoard[i][j]=='r' && j<5   && i>2 && (nextBoard[i-1][j+1]=='B'|| nextBoard[i-1][j+1]=='b') &&  (nextBoard[i-2][j+2]=='B'|| nextBoard[i-2][j+2]=='b') && nextBoard[i-3][j+3]=='-'){
                              tempBoard = action(i, j, i-3, j+3, 2, nextBoard);
                                    int alpha=Integer.MAX_VALUE, beta=Integer.MIN_VALUE;
                             temp=maxValue(tempBoard, 0,alpha, beta);
                              beta=Math.min(alpha, temp);
                              flag=2;
//                                temp = action(i,j, i-2, j+2, 2);
                        if(temp < v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-3;
                            j2=j+3;
                           
                        }
                    }
                        if(nextBoard[i][j]=='r' && j<5   && i<5 && (nextBoard[i+1][j+1]=='B'|| nextBoard[i+1][j+1]=='b') &&(nextBoard[i+2][j+2]=='B'|| nextBoard[i+2][j+2]=='b') && nextBoard[i+3][j+3]=='-'){
//                                temp = action(i,j, i+2, j+2, 2);
                                  tempBoard = action(i, j, i+3, j+3, 2, nextBoard);
                                int alpha=Integer.MAX_VALUE, beta=Integer.MIN_VALUE;
                              temp=maxValue(tempBoard, 0,alpha, beta);
                              beta=Math.min(alpha, temp);
                              flag=2;
                        if(temp <v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i+3;
                            j2=j+3;
                            
                        }
                    }
                        
                        if(flag!=2 && nextBoard[i][j]=='r' && i>1 && j>1 && nextBoard[i-2][j-2]=='-' && (nextBoard[i-1][j-1]=='B'|| nextBoard[i-1][j-1]=='b')){
                            tempBoard = action(i, j, i-2, j-2, 2, nextBoard);
                            int alpha=Integer.MAX_VALUE, beta=Integer.MIN_VALUE;
                              temp=maxValue(tempBoard, 0,alpha, beta);
                              beta=Math.min(alpha, temp);
                              flag=1;
//                        temp = action(i, j, i+1, j+1, 2);
                        if(temp < v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-2;
                            j2=j-2;
                            
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        }
                        if(flag!=2 &&  nextBoard[i][j]=='r' && i<6 && j>1 && nextBoard[i+2][j-2]=='-' && (nextBoard[i+1][j-1]=='B'|| nextBoard[i+1][j-1]=='b')){
                            tempBoard = action(i, j, i+2, j-2, 2, nextBoard);
                             int alpha=Integer.MAX_VALUE, beta=Integer.MIN_VALUE;
                             temp=maxValue(tempBoard, 0,alpha, beta);
                              beta=Math.min(alpha, temp);
                             flag=1;
//                        temp = action(i, j, i+1, j+1, 2);
                            if(temp <= v)
                            {
    //                            temp=v;
                                v=temp;
                                i1=i;
                                j1=j;
                                i2=i+2;
                                j2=j-2;
                                
    //                            nextBoard= getnext(i, j, i+1, j-1, 1);
                            }
                        }
                       
                        if(flag!=2 && j<6   && i>1 && (nextBoard[i-1][j+1]=='B'|| nextBoard[i-1][j+1]=='b') && nextBoard[i-2][j+2]=='-'){
                              tempBoard = action(i, j, i-2, j+2, 2, nextBoard);
                                    int alpha=Integer.MAX_VALUE, beta=Integer.MIN_VALUE;
                             temp=maxValue(tempBoard, 0,alpha, beta);
                              beta=Math.min(alpha, temp);
                              flag=1;
//                                temp = action(i,j, i-2, j+2, 2);
                        if(temp <= v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-2;
                            j2=j+2;
                            flag=1;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                    }
                        if(flag!=2 && j<6   && i<6 && (nextBoard[i+1][j+1]=='B'|| nextBoard[i+1][j+1]=='b') && nextBoard[i+2][j+2]=='-'){
//                                temp = action(i,j, i+2, j+2, 2);
                                  tempBoard = action(i, j, i+2, j+2, 2, nextBoard);
                                int alpha=Integer.MAX_VALUE, beta=Integer.MIN_VALUE;
                              temp=maxValue(tempBoard, 0,alpha, beta);
                              beta=Math.min(alpha, temp);
                              flag=1;
                        if(temp <=v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i+2;
                            j2=j+2;
                            flag=1;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                    }
        }
     
      if(flag==0)
           return 0;
     else{
         if(flag==1 || flag==2){
             if(flag==1)
                nBlack--;
             else nBlack-=2;
             nextBoard=action(i1,j1,i2,j2, 2, nextBoard);
             for(int ii=0; ii<8; ii++){
            for(int jj=0; jj<8; jj++){
                if(jj==0 && nextBoard[ii][jj]=='B')
                        nextBoard[ii][jj]='b';
                if(jj==7 && nextBoard[ii][jj]=='R')
                        nextBoard[ii][jj]='r';
                    matrix[ii][jj]=nextBoard[ii][jj];
                }
                }
             repaint();
             
         }
         return anotherkillfor2(i2, j2, nextBoard);
     }
     
     
 }
 int getPossibleMovesfor1(int i, int j, int f){
        
        if(i>0 && j>0 && matrix[i-1][j-1]=='-'){
            return 1;
        }
        if(i<6 && j>0 && matrix[i+1][j-1]=='-'){
            return 1;
        }
        if(f==1 && i>0 && j<6 && matrix[i-1][j+1]=='-')
            return 1;
        if(f==1 && i<6 && j<6 && matrix[i+1][j+1]=='-')
            return 1;
        
        return 0;
//        if()
    }
   void findBestMovefor1() throws InterruptedException{ //BLACK
        
        char[][] nextBoard;
           System.out.println("BLACK TURN");
        nextBoard= new char[8][8];
         char[][] tempBoard;
        tempBoard= new char[8][8];
         for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                 nextBoard[i][j]=matrix[i][j];
            }
        }
       int possMoves=0;
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(nextBoard[i][j]=='B')
                possMoves+=getPossibleMovesfor1(i,j,0);
                
                else if(nextBoard[i][j]=='b')
                possMoves+=getPossibleMovesfor1(i,j,1);
            }
        }
        if(possMoves==0)
        {
            nBlack=0;
            return;
        }
        int v=-99999, temp,flag=0;
        int i2=0, j2=0,i1=0,j1=0;
        
        for(int i=0; i<8;i++){
            for(int j=0; j<8; j++){
                if(nextBoard[i][j]=='B' || nextBoard[i][j]=='b'){
//                        System.out.println(i + " " +j);
                    if(nextBoard[i][j]=='b' && i<5 && j<5 && nextBoard[i+3][j+3]=='-' &&( nextBoard[i+1][j+1]=='R' ||  nextBoard[i+1][j+1]=='r') && ( nextBoard[i+2][j+2]=='R' ||  nextBoard[i+2][j+2]=='r')){
                        tempBoard = action(i, j, i+3, j+3, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                        temp=minValue(tempBoard, 0, alpha, beta);
                        flag=2;
                        
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i+3;
                            j2=j+3;
                            flag=2;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }
                    if(nextBoard[i][j]=='b' && i>2 && j<5 && nextBoard[i-3][j+3]=='-' &&( nextBoard[i-1][j+1]=='R' ||  nextBoard[i-1][j+1]=='r') &&( nextBoard[i-2][j+2]=='R' ||  nextBoard[i-2][j+2]=='r')){
                        tempBoard = action(i, j, i-3, j+3, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                        temp=minValue(tempBoard, 0,alpha, beta);
                        
                        flag=2;
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-3;
                            j2=j+3;
                            flag=2;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }
                    if(nextBoard[i][j]=='b' && i>2 && j>2 &&(nextBoard[i-1][j-1]=='R' || nextBoard[i-1][j-1]=='r') && (nextBoard[i-2][j-2]=='R' || nextBoard[i-2][j-2]=='r') && nextBoard[i-3][j-3]=='-' ){
                         tempBoard = action(i, j, i-3, j-3, 1, nextBoard);
                       int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                         temp=minValue(tempBoard, 0,alpha, beta);
                        flag=2;
//                         temp=action(i, j, i-2, j-2, 1);
                         if(temp >= v)
                        {
                            i1=i;
                            j1=j;
                             v=temp;
                             i2=i-3;
                            j2=j-3;
                              flag=1;
//                            nextBoard= getnext(i, j, i-2, j-2, 1);
                        }

                    }
//
                    if(nextBoard[i][j]=='b' &&i<5 && j>2 && (nextBoard[i+1][j-1]=='R' || nextBoard[i+1][j-1]=='r') &&(nextBoard[i+2][j-2]=='R' || nextBoard[i+2][j-2]=='r') && nextBoard[i+3][j-3]=='-' ){
                        flag=2;
                          tempBoard = action(i, j, i+3, j-3, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                        temp=minValue(tempBoard, 0,alpha, beta);
                        
                         if(temp >= v)
                        {
                            i1=i;
                            j1=j;
                             v=temp;
                             i2=i+3;
                             j2=j-3;
//                              flag=1;
//                            nextBoard= getnext(i, j, i-1, j-1, 1);
                        }
//                            nextBoard=action(i,j, i+2, j-2, 1);
                    }
                    if(flag!=2 && nextBoard[i][j]=='b' && i<6 && j<6 && nextBoard[i+2][j+2]=='-' &&( nextBoard[i+1][j+1]=='R' ||  nextBoard[i+1][j+1]=='r')){
                        tempBoard = action(i, j, i+2, j+2, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                        temp=minValue(tempBoard, 0, alpha, beta);
                        flag=1;
                        
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i+2;
                            j2=j+2;
                            flag=1;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }
                     if(flag!=2 && nextBoard[i][j]=='b' && i>1 && j<6 && nextBoard[i-2][j+2]=='-' &&( nextBoard[i-1][j+1]=='R' ||  nextBoard[i-1][j+1]=='r')){
                        tempBoard = action(i, j, i-2, j+2, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                        temp=minValue(tempBoard, 0,alpha, beta);
                        
                        flag=1;
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-2;
                            j2=j+2;
                            flag=1;
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }
                      if(flag!=2 && i>1 && j>1 &&(nextBoard[i-1][j-1]=='R' || nextBoard[i-1][j-1]=='r') && nextBoard[i-2][j-2]=='-' ){
                         tempBoard = action(i, j, i-2, j-2, 1, nextBoard);
                       int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                         temp=minValue(tempBoard, 0,alpha, beta);
                        flag=1;
//                         temp=action(i, j, i-2, j-2, 1);
                         if(temp >= v)
                        {
                            i1=i;
                            j1=j;
                             v=temp;
                             i2=i-2;
                            j2=j-2;
                              flag=1;
//                            nextBoard= getnext(i, j, i-2, j-2, 1);
                        }

                    }
//
                    if(flag!=2 && i<6 && j>1 && (nextBoard[i+1][j-1]=='R' || nextBoard[i+1][j-1]=='r') && nextBoard[i+2][j-2]=='-' ){
                        flag=1;
                          tempBoard = action(i, j, i+2, j-2, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                        temp=minValue(tempBoard, 0,alpha, beta);
                        
                         if(temp >= v)
                        {
                            i1=i;
                            j1=j;
                             v=temp;
                             i2=i+2;
                             j2=j-2;
                              flag=1;
//                            nextBoard= getnext(i, j, i-1, j-1, 1);
                        }
//                            nextBoard=action(i,j, i+2, j-2, 1);
                    }

                    if(flag==0 && i!=7  && j!=0 && nextBoard[i+1][j-1]=='-'){
                        tempBoard = action(i, j, i+1, j-1, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                       temp=minValue(tempBoard, 0,alpha, beta);
                        
                        
                        if(temp >= v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i+1;
                            j2=j-1;
//                           
                        }
                    }
                            
                    if(flag==0 && i!=0  && j!=0 && nextBoard[i-1][j-1]=='-'&& nextBoard[i][j]=='b'){
                         tempBoard = action(i, j, i-1, j-1, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                         temp=minValue(tempBoard, 0,alpha, beta);
                       
                         if(temp > v)
                        {
                            i1=i;
                            j1=j;
                             v=temp;
                             i2=i-1;
                            j2=j-1;
//                            nextBoard= getnext(i, j, i-1, j-1, 1);
                        }
                    }
                    if(flag==0 && i!=0  && j!=0 && nextBoard[i-1][j-1]=='-'&& nextBoard[i][j]=='B'){
                         tempBoard = action(i, j, i-1, j-1, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                         temp=minValue(tempBoard, 0,alpha, beta);
                       
                         if(temp > v)
                        {
                            i1=i;
                            j1=j;
                             v=temp;
                             i2=i-1;
                            j2=j-1;
//                            nextBoard= getnext(i, j, i-1, j-1, 1);
                        }
                    }
                    
                    if(flag==0 && nextBoard[i][j]=='b' && i!=7 && j!=7 && nextBoard[i+1][j+1]=='-'){
                        tempBoard = action(i, j, i+1, j+1, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                         temp=minValue(tempBoard, 0,alpha, beta);
                       
                        
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i+1;
                            j2=j+1;
//                          
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }
                    if(flag==0 && nextBoard[i][j]=='b' && i!=0 && j!=7 && nextBoard[i-1][j+1]=='-'){
                        tempBoard = action(i, j, i-1, j+1, 1, nextBoard);
                        int alpha=Integer.MIN_VALUE, beta=Integer.MAX_VALUE;
                         temp=minValue(tempBoard, 0,alpha, beta);
                        alpha=Math.max(alpha, temp);
                        
                        
                        if(temp > v)
                        {
//                            temp=v;
                            v=temp;
                            i1=i;
                            j1=j;
                            i2=i-1;
                            j2=j+1;
                            
//                            nextBoard= getnext(i, j, i+1, j-1, 1);
                        }
                        
                    }
                    
                            
                   
                    
                   
                }
            }
        }
//      if(v==-99999)
//        {
//            
//            nBlack=0;
//        }
        nextBoard=action(i1,j1,i2,j2, 1, nextBoard);
        if(Math.abs(i1-i2)==2){
             nRed--;
             
        }
        else if(Math.abs(i1-i2)==3){
          nRed-=2;
        }
           
          for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(j==0 && nextBoard[i][j]=='B')
                        nextBoard[i][j]='b';
                if(j==7 && nextBoard[i][j]=='R')
                        nextBoard[i][j]='r';
                    matrix[i][j]=nextBoard[i][j];
            }
        }
          if(Math.abs(i1-i2)==1)
              return;
         int k=1;
         while(k!=0){
             k=anotherkillfor1(i2, j2, nextBoard);
             Thread.sleep(500);
         }
         return;
//       
//         return nextBoard;
    }
    int getPossibleMovesfor2(int i, int j){
        
        if(i>0 && j<6 && matrix[i-1][j+1]=='-'){
            return 1;
        }
        if(i<6 && j<6 && matrix[i+1][j+1]=='-'){
            return 1;
        }
        return 0;
//        if()
    }
   int nextMovefor2(int i1, int j1, int i2, int j2){
       if(!(matrix[i1][j1]=='R' || matrix[i2][j2]=='r')){
           return 0;
       }

           if(Math.abs(i1-i2)==2){
               if(i2>=0 && j2<=7 && i2==i1-2 && j2==j1+2 && (matrix[i1-1][j1+1]=='B'||matrix[i1-1][j1+1]=='b') && matrix[i2][j2]=='-'){
                   matrix[i1-1][j1+1]='-';
                   
                   if(matrix[i1][j1]=='R')
                    matrix[i1-2][j1+2]='R';
                   else 
                        matrix[i1-2][j1+2]='r';
                   matrix[i1][j1]='-';
                   return 1;
               }
               if(i2<=7 && j2<=7 && i2==i1+2 && j2==j1+2 && (matrix[i1+1][j1+1]=='B'||matrix[i1+1][j1+1]=='b') && matrix[i2][j2]=='-'){
                   matrix[i1+1][j1+1]='-';
                   
                   if(matrix[i1][j1]=='R')
                    matrix[i1+2][j1+2]='R';
                   else 
                        matrix[i1+2][j1+2]='r';
                   matrix[i1][j1]='-';
                   return 1;
               }
               if(i2>=0 && j2>=0 && matrix[i1][j1]=='r' && i2==i1-2 && j2==j1-2 && (matrix[i1-1][j1-1]=='B'||matrix[i1-1][j1-1]=='b') && matrix[i2][j2]=='-'){
                   matrix[i1-1][j1-1]='-';
                   
                   
                        matrix[i1-2][j1-2]='r';
                   matrix[i1][j1]='-';
                   return 1;
               }
               
               if(i2<=7 && j2>=0 &&matrix[i1][j1]=='r' && i2==i1+2 && j2==j1-2 && (matrix[i1+1][j1-1]=='B'||matrix[i1+1][j1-1]=='b') && matrix[i2][j2]=='-'){
                   matrix[i1+1][j1-1]='-';
                   
                   
                        matrix[i1+2][j1-2]='r';
                   matrix[i1][j1]='-';
                   return 1;
               }
               
           }
           else{
               if( matrix[i1][j1]=='r' && i1!=0 && j1!=0 && i2==i1-1 && j2==j1-1 && matrix[i1-1][j1-1]=='-'){
                             matrix[i2][j2]='r';
                              matrix[i1][j1]='-';
                                return 1;
                        }
               
               if( matrix[i1][j1]=='r' &&  i1!=7 && j1!=0 && matrix[i1+1][j1-1]=='-' && i2==i1+1 && j2==j1-1){
                             matrix[i2][j2]='r';
                              matrix[i1][j1]='-';
                                return 1;
                        }
               if(  i1!=7 && j1!=7 && matrix[i1+1][j1+1]=='-' && i2==i1+1 && j2==j1+1){
                            if(matrix[i1][j1]=='R')
                                 matrix[i2][j2]='R';
                            else
                                matrix[i2][j2]='r';
                              matrix[i1][j1]='-';
                               return 1;
                        }
                   if(  i1!=0 && j1!=7 && matrix[i1-1][j1+1]=='-' && i2==i1-1 && j2==j1+1){
                            if(matrix[i1][j1]=='R')
                                 matrix[i2][j2]='R';
                            else
                                matrix[i2][j2]='r';
                              matrix[i1][j1]='-';
                                return 1;
                        }
               
               
               
               
           }
       
       
       
       return 0;
   }
   int nextMovefor1(int i1, int j1, int i2, int j2){
       if(!(matrix[i1][j1]=='B' || matrix[i2][j2]=='b')){
           return 0;
       }

           if(Math.abs(i1-i2)==2){
               if(i2>=0 && j2>=0 && i2==i1-2 && j2==j1-2 && (matrix[i1-1][j1-1]=='R'||matrix[i1-1][j1-1]=='r') && matrix[i2][j2]=='-'){
                   matrix[i1-1][j1-1]='-';
                   
                   if(matrix[i1][j1]=='B')
                    matrix[i1-2][j1-2]='B';
                   else 
                        matrix[i1-2][j1-2]='b';
                   matrix[i1][j1]='-';
                   return 1;
               }
               if(i2<=7 && j2>=0 && i2==i1+2 && j2==j1-2 && (matrix[i1+1][j1-1]=='B'||matrix[i1+1][j1-1]=='b') && matrix[i2][j2]=='-'){
                   matrix[i1+1][j1-1]='-';
                   
                   if(matrix[i1][j1]=='B')
                    matrix[i1+2][j1-2]='B';
                   else 
                        matrix[i1+2][j1-2]='b';
                   matrix[i1][j1]='-';
                   return 1;
               }
               if(i2>=0 && j2<=7 &&matrix[i1][j1]=='b' && i2==i1-2 && j2==j1+2 && (matrix[i1-1][j1+1]=='R'||matrix[i1-1][j1+1]=='r') && matrix[i2][j2]=='-'){
                   matrix[i1-1][j1+1]='-';
                   
                   
                        matrix[i1-2][j1+2]='b';
                   matrix[i1][j1]='-';
                   return 1;
               }
               
               if(i2<=7 && j2<=7 &&matrix[i1][j1]=='b' && i2==i1+2 && j2==j1+2 && (matrix[i1+1][j1+1]=='R'||matrix[i1+1][j1+1]=='r') && matrix[i2][j2]=='-'){
                   matrix[i1+1][j1+1]='-';
                   
                   
                        matrix[i1+2][j1+2]='b';
                   matrix[i1][j1]='-';
                   return 1;
               }
               
           }
           else{
               if(i2>=0 && j2<=7 && matrix[i1][j1]=='b' && i1!=0 && j1!=7 && i2==i1-1 && j2==j1+1 && matrix[i1-1][j1+1]=='-'){
                             matrix[i2][j2]='b';
                              matrix[i1][j1]='-';
                                return 1;
                        }
               
               if( i2<=7 && j2<=7 && matrix[i1][j1]=='b' &&  i1!=7 && j1!=7 && matrix[i1+1][j1+1]=='-' && i2==i1+1 && j2==j1+1){
                             matrix[i2][j2]='b';
                              matrix[i1][j1]='-';
                                return 1;
                        }
               if(  i1!=7 && j1!=0 && matrix[i1+1][j1-1]=='-' && i2==i1+1 && j2==j1-1){
                            if(matrix[i1][j1]=='B')
                                 matrix[i2][j2]='B';
                            else
                                matrix[i2][j2]='b';
                              matrix[i1][j1]='-';
                               return 1;
                        }
                   if(  i1!=0 && j1!=0 && matrix[i1-1][j1-1]=='-' && i2==i1-1 && j2==j1-1){
                            if(matrix[i1][j1]=='B')
                                 matrix[i2][j2]='B';
                            else
                                matrix[i2][j2]='b';
                              matrix[i1][j1]='-';
                                return 1;
                        }
               
               
               
               
           }
       
       
       
       return 0;
   }
   int getx(){
       return x/50;
   }
   int gety(){
       return y/50;
   }
    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        
//        System.out.println(x/50+" "+y/50);
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

public class CheckersHumanVSHuman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
      
        Board b = new Board();
//        
        b.printIt();
        int player=1;
        int m=0;
        JFrame f =  new JFrame();
        f.setBounds(10,10,500,1000);
        f.setResizable(true);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(b);
        Scanner sc=new Scanner(System.in);
        int xOriginal =-5, yOriginal=-5;
//        int k=0;
          b.x=-3;
            b.y=-3;
//          
        while(m==0){
            if(b.nRed==0 || b.nBlack==0){
//                
                if(b.nBlack==0)
                   System.out.println("Red won the game");
                else
                    System.out.println("Black won the game");
                
//                break;
            }
            int t=0;
            if(player==1){
               int t2=0;
//               
                Thread.sleep(5000);
//              
                while(t==0 || t2==0){
                    Thread.sleep(5000);
                   System.out.println("Select peg");
                   Thread.sleep(1000);
//                    System.out.println(b.x/50 +" "+b.y/50);
                   int x1=b.x/50;
//                   String k=sc.nextLine();
                   int y1=b.y/50;
                   System.out.println(x1+" "+y1);
                   Thread.sleep(5000);
                   System.out.println("Select peg destination");
                    Thread.sleep(1000);
//               
                   int x2=b.x/50;
                   int y2=b.y/50;
                   System.out.println(x2+" "+y2);
                   if(x1>=0 && x1<=7 &&x2>=0 && x2<=7 &&y1>=0 && y1<=7 &&y2>=0 && y2<=7 )
                   {
                       if(!(x1==x2 &&y1==y2) ){
                           t2= b.nextMovefor1(x1,y1, x2, y2);
                       t=1;
                        player=1;
                         b.printIt();
                
                        b.repaint();
                       }
                      
                   }
                }
                player=2;
//                b.printIt();
//                
//                b.repaint();
//                Thread.sleep(500);
            }
            else{
                int t2=0;
                t=0;
//               
                Thread.sleep(5000);
//              
                while(t==0 || t2==0){
                    Thread.sleep(5000);
                   System.out.println("Select peg");
                   Thread.sleep(1000);
//                    System.out.println(b.x/50 +" "+b.y/50);
                   int x1=b.x/50;
//                   String k=sc.nextLine();
                   int y1=b.y/50;
                   System.out.println(x1+" "+y1);
                   Thread.sleep(5000);
                   System.out.println("Select peg destination");
                    Thread.sleep(1000);
//               
                   int x2=b.x/50;
                   int y2=b.y/50;
                   System.out.println(x2+" "+y2);
                   if(x1>=0 && x1<=7 &&x2>=0 && x2<=7 &&y1>=0 && y1<=7 &&y2>=0 && y2<=7 )
                   {
                       if(!(x1==x2 &&y1==y2) ){
                           t2= b.nextMovefor2(x1,y1, x2, y2);
                       t=1;
                        player=1;
                         b.printIt();
                
                        b.repaint();
                       }
                      
                   }
                }
                
                   
            }
            
            
//          m++;  
        }
//        
        System.out.println("KHATAM");
        
    }
    
}
