# Base image, see https://hub.docker.com/r/rocker/rstudio
FROM rocker/rstudio:4.0.3

# Install rJava
RUN apt-get -y update && apt-get install -y \
   r-cran-rjava \
   && apt-get clean \
   && rm -rf /var/lib/apt/lists/

RUN R -e "install.packages(c('rJava','rkafkajars'))"
