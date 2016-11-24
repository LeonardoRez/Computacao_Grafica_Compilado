//para compilar execute: gcc -lGL -lGLU -lglut teste.c -o teste

#include <GL/gl.h>
#include <GL/glut.h>

void draw(void)
{
  //Background color
  glClearColor(1,1,1,1);
  glClear(GL_COLOR_BUFFER_BIT );


	// projecao do plano cartesiano
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();

	// limitar o plano cartesiano
	gluOrtho2D(-3, 3, -3, 3); // limitado por um quadrado de lado 3
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	//Quadrado com degrade de vermelho para amarelo
	glBegin(GL_QUADS);
	
	glColor3f(1,1,0);

	glVertex2f(2, -2);	
	glVertex2f(2, 2);

	glColor3f(1,0,0);

	glVertex2f(-2, 2);
	glVertex2f(-2, -2);

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

