#include <GL/glut.h>

GLfloat difusor_luz[] = {1.0, 1.0, 0.0, 1.0};  /* cor do cubo */
GLfloat posicao_luz[] = {1.0, 1.0, 1.0, 0.0};  /* luz posicionada no infinito*/
GLfloat n[6][3] = {  /* vetor normal pras 6 faces do cubo */
  {-1.0, 0.0, 0.0}, {0.0, 1.0, 0.0}, {1.0, 0.0, 0.0},
  {0.0, -1.0, 0.0}, {0.0, 0.0, 1.0}, {0.0, 0.0, -1.0} };
GLint faces[6][4] = {  /* Os indices das 6 faces do cubo. */
  {0, 1, 2, 3}, {3, 2, 6, 7}, {7, 6, 5, 4},
  {4, 5, 1, 0}, {5, 6, 2, 1}, {7, 4, 0, 3} };
GLfloat v[8][3];  /* guarda as coordenadas X,Y,Z. */


void drawCubo()
{

  for (int i = 0; i < 6; i++) {
    glBegin(GL_QUADS);
    glNormal3fv(&n[i][0]);
    glVertex3fv(&v[faces[i][0]][0]);
    glVertex3fv(&v[faces[i][1]][0]);
    glVertex3fv(&v[faces[i][2]][0]);
    glVertex3fv(&v[faces[i][3]][0]);
    glEnd();
  }
}

void display()
{
  glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  drawCubo();
  glutSwapBuffers();
}

void init()
{
  // configuração dos vértices do cubo
  v[0][0] = v[1][0] = v[2][0] = v[3][0] = -1;
  v[4][0] = v[5][0] = v[6][0] = v[7][0] = 1;
  v[0][1] = v[1][1] = v[4][1] = v[5][1] = -1;
  v[2][1] = v[3][1] = v[6][1] = v[7][1] = 1;
  v[0][2] = v[3][2] = v[4][2] = v[7][2] = 1;
  v[1][2] = v[2][2] = v[5][2] = v[6][2] = -1;

  //luz
  glLightfv(GL_LIGHT0, GL_DIFFUSE, difusor_luz);
  glLightfv(GL_LIGHT0, GL_POSITION, posicao_luz);
  glEnable(GL_LIGHT0);
  glEnable(GL_LIGHTING);

  //tira objetos que não aparecem
  glEnable(GL_DEPTH_TEST);

  //configuração da projeção
  glMatrixMode(GL_PROJECTION);
  gluPerspective( /* angulo*/ 40.0, 1.0,
    /* proximidade do eixo z*/ 1.0, /* afastamento do eixo z */ 10.0);
  glMatrixMode(GL_MODELVIEW);
  gluLookAt(0.0, 0.0, 5.0,  /* obeservador */
    0.0, 0.0, 0.0,      /* centro */
    0.0, 1.0, 0.);      /* parte de cima do observador acompanha eixo y */

  // ajusta o cubo (angulo, em x, em y, em z
  glTranslatef(0.0, 0.0, -1.0);
  glRotatef(60, 1.0, 0.0, 0.0);
  glRotatef(-20, 0.0, 0.0, 1.0); 
}

int
main(int argc, char **argv)
{
  glutInit(&argc, argv);
  glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
  glutCreateWindow("red 3D lighted cube");
  glutDisplayFunc(display);
  init();
  glutMainLoop();
  return 0;
}
