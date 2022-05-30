SUMMARY = "Chipsee customized image."
DESCRIPTION = "Custom image for chipsee devices"

LICENSE = "MIT"

IMAGE_INSTALL = "packagegroup-core-boot \
        packagegroup-core-ssh-openssh \
        packagegroup-self-hosted \
        packagegroup-core-boot \
        ${CORE_IMAGE_EXTRA_INSTALL} \
        udev-extra-rules \
        networkmanager \
        vim"

inherit core-image

IMAGE_BASENAME = "chipsee"
IMAGE_NAME = "${IMAGE_BASENAME}_${MACHINE}"
IMAGE_FEATURES += "x11-base package-management splash"

QB_MEM = '${@bb.utils.contains("DISTRO_FEATURES", "opengl", "-m 512", "-m 256", d)}'

IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"

IMAGE_LINGUAS = "en-us"
SYSTEMD_DEFAULT_TARGET = "multi-user.target"

inherit extrausers
USER     = "user" 
USER_ID  = "1000"
USER_GID = "1000"

EXTRA_USERS_PARAMS = "\
        groupadd --gid ${USER_GID} ${USER}; \
        useradd \
         --password '$6$bAEDhG1yMfKjdEfQ$zT1TMnVYeXBSFW5FJ2911nr3dlKZ3uqZ3HqB/nbH9qQ5fy0QWsmQ2nVQ4BqiiV3MNEM07qfjp8Qz0iXmahXKP0' \
         --uid ${USER_ID} \
         --gid ${USER_GID} \
         --shell /bin/bash \
         --create-home \
         --system \
         ${USER}; "
