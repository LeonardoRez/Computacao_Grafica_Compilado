//para compilar execute: gcc -lGL -lGLU -lglut teste.c -o teste

#include <GL/gl.h>
#include <GL/glut.h>

#include <math.h>



void desenhaCirculo(float cx, float cy, float r, int quantSegmentos)
{
    glBegin(GL_LINE_LOOP);
    for(int ii = 0; ii < quantSegmentos; ii++)
    {
        float a = 2.0f * 3.1415926f * float(ii) / float(quantSegmentos);//angulo

        float x = r * cosf(a);
        float y = r * sinf(a);

        glVertex2f(x + cx, y + cy);

    }
    glEnd();
}
void draw() {
	
	glClearColor(1,1,1,1);
	glClear(GL_COLOR_BUFFER_BIT );


	// projecao do plano cartesiano
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();

	// dimensão do plano cartesiano
	gluOrtho2D(-3, 3, -3, 3); //6x6
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

    glBegin(GL_QUADS); //começando a desenhar o quadrado
      glColor3f (0.0, 1, 0.0);//cor verde
      glVertex2f (2, -2);
      glVertex2f (-2, -2);
      glVertex2f (-2, 2);
      glVertex2f (2, 2);
    glEnd();

      glColor3f (0.0, 0.0, 1.0); //mudando a cor para azul
      desenhaCirculo(0, 0,1, 50);

	glBegin(GL_TRIANGLES);//triangulo
      glColor3f (1, 0, 0); //ponta superior vermelha
      glVertex2f (0,0.5);
      glColor3f (0, 0, 0);//base preta
      glVertex2f (-0.5, -0.25);
      glVertex2f (0.5, -0.25);
	glEnd();
    glFlush();
}



//Main program
int main(int argc, char **argv)
{
  glutInit(&argc, argv);
  //Simple buffer
  glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB );
  glutInitWindowPosition(50,25);
  glutInitWindowSize(500,500);
  glutCreateWindow("t4");

  //Call to the drawing function
  glutDisplayFunc(draw);
  glutMainLoop();

  return 0;
}

