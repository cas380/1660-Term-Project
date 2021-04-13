# Dockerfile for microservices-gui
FROM python

# Update package list
RUN apt update
RUN apt install -y libgl1-mesa-glx
RUN pip install orange3

# produces the error below
ENTRYPOINT orange-canvas

# leave container hanging to exec
#ENTRYPOINT ["tail", "-f", "/dev/null"]


#=========================================================#
# None of the solutions for this online work so I give up #
#=========================================================#

# qt.qpa.plugin: Could not load the Qt platform plugin "xcb" in "" even though it was found.
# This application failed to start because no Qt platform plugin could be initialized. Reinstalling the application may fix this problem.
#
# Available platform plugins are: eglfs, linuxfb, minimal, minimalegl, offscreen, vnc, wayland-egl, wayland, wayland-xcomposite-egl, wayland-xcomposite-glx, webgl, xcb.

# find / -name PyQt5
# /usr/local/lib/python3.9/site-packages/PyQt5