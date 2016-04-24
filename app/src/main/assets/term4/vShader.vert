
attribute vec3 aPos;
attribute vec2 aTexCoord;

varying vec2 vTexCoord;

uniform mat4 uProjMat;
uniform mat4 uWorldMat;

void main()
{
    gl_Position = uProjMat * uWorldMat * vec4(aPos, 1);
    vTexCoord = aTexCoord;
}