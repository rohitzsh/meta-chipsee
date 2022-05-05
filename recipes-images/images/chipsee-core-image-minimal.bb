SUMMARY = "Chipsee customized image."
DESCRIPTION = "Custom image for chipsee devices"

LICENSE = "MIT"

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

inherit core-image

IMAGE_BASENAME = "chipsee"
MACHINE_NAME ?= "${MACHINE}"
IMAGE_NAME = "${IMAGE_BASENAME}_${MACHINE_NAME}"

IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"
IMAGE_INSTALL_append = " udev-extra-rules "

IMAGE_LINGUAS = "en-us"

IMAGE_DEV_MANAGER   = "udev"
IMAGE_INIT_MANAGER  = "systemd"
IMAGE_INITSCRIPTS   = " "
IMAGE_LOGIN_MANAGER = "busybox shadow"

SYSTEMD_DEFAULT_TARGET = "multi-user.target"

inherit extrausers
USER     = "user" 
USER_ID  = "1000"
USER_GID = "1000"

EXTRA_USERS_PARAMS = "\
        groupadd --gid ${USER_GID} ${USER}; \
        useradd  --uid ${USER_ID} --gid ${USER_GID} --shell /bin/bash --system ${USER}; "
