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
void draw(void)
{
  //Background color
  glClearColor(1,1,1,1);
  glClear(GL_COLOR_BUFFER_BIT );


	// projecao do plano cartesiano
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();

	// limitar o plano cartesiano
	gluOrtho2D(-10, 10, -10, 10);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();


	//cabeça
	glColor3f(0,0,0);
	desenhaCirculo(-6.0, 1.0, 0.6, 70);
	//corpo
	glBegin(GL_LINES);
	glVertex2f(-6, 0.4);
	glVertex2f(-6, -2);
//bracos	
	glVertex2f(-6, 0.3);
	glVertex2f(-5, -1);
	glVertex2f(-6, 0.3);
	glVertex2f(-7, -1);
//pernas
	glVertex2f(-6, -2);
	glVertex2f(-5.5, -3.3);
	glVertex2f(-6, -2);
	glVertex2f(-6.5, -3.3);
//casa
	glVertex2f(0, 4);//parede1
	glVertex2f(0, -3.3);
	glVertex2f(2.5, 4);//parede2
	glVertex2f(2.5, -3.3);
	glVertex2f(8, 4);//parede3
	glVertex2f(8, -3.3);
	glVertex2f(0, -3.3);//chao
	glVertex2f(8, -3.3);
	glVertex2f(0, 4);//forro
	glVertex2f(8, 4);
//telhado
	glVertex2f(0, 4);
	glVertex2f(1.25, 5.5);

	glVertex2f(2.5, 4);
	glVertex2f(1.25, 5.5);

	glVertex2f(1.25, 5.5);
	glVertex2f(6.5, 5.5);

	glVertex2f(6.5, 5.5);
	glVertex2f(8, 4);
//porta
	glVertex2f(0.25, -3.3);
	glVertex2f(0.25, 2.5);
	glVertex2f(2.25, -3.3);
	glVertex2f(2.25, 2.5);
	glVertex2f(0.25, 2.5);
	glVertex2f(2.25, 2.5);
	desenhaCirculo(2,0.3,0.1,5000);

//janela
	glBegin(GL_LINES);
	glVertex2f(3.5, 0.25);
	glVertex2f(5.25, 0.25);

	glVertex2f(3.5, 2.0);
	glVertex2f(3.5, 0.25);

	glVertex2f(4.375, 2.0);
	glVertex2f(4.375, 0.25);

	glVertex2f(3.5, 1.175);
	glVertex2f(5.25, 1.175);

	glVertex2f(3.5, 2);
	glVertex2f(5.25, 2);

	glVertex2f(5.25, 2);
	glVertex2f(5.25, 0.25);

	



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
  glutCreateWindow("Minha Janela");

  //Call to the drawing function
  glutDisplayFunc(draw);
  glutMainLoop();

  return 0;
}

