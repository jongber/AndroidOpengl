
attribute vec3 aColor;
attribute vec3 aPos;     //  attribute - > in, version es 300

varying vec3 vColor;

uniform mat4 uProjMat;
uniform mat4 uWorldMat;

void main()
{
    gl_Position = uProjMat * uWorldMat * vec4(aPos, 1);
    vColor = aColor;
}