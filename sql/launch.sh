# this requires either docker to be installed or a compatible alternative like podman to be aliased as docker
# it can be installed at https://www.docker.com/
# to initialise run the file as is.
docker run --volume=PROJECT-pg:/var/lib/postgresql/data --name=PROJECTDB -p 5432:5432 -e POSTGRES_PASSWORD=yaydatayaybase -d postgres:15
# if this fails you may need to download the postgres image. run the below to download it
# docker pull postgres:15
# to stop run:
# docker stop PROJECTDB 
# to start it up again run:
# docker start PROJECTDB
 