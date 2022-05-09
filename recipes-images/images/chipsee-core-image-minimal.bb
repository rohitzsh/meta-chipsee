SUMMARY = "Chipsee customized image."
DESCRIPTION = "Custom image for chipsee devices"

LICENSE = "MIT"

IMAGE_INSTALL = "packagegroup-core-boot \
        packagegroup-core-ssh-openssh \
        packagegroup-self-hosted \
        packagegroup-core-boot \
        ${CORE_IMAGE_EXTRA_INSTALL}"

inherit core-image

IMAGE_BASENAME = "chipsee"
MACHINE_NAME ?= "${MACHINE}"
IMAGE_NAME = "${IMAGE_BASENAME}_${MACHINE_NAME}"
IMAGE_FEATURES += "x11-base package-management splash"

QB_MEM = '${@bb.utils.contains("DISTRO_FEATURES", "opengl", "-m 512", "-m 256", d)}'

IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"
IMAGE_INSTALL_append = "\
         udev-extra-rules"

IMAGE_LINGUAS = "en-us"

# Do a quiet boot with limited console messages
APPEND += "rootfstype=ext4 quiet"

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
        useradd \
         --password '$6$zYfF6j3d1fp3uwHd$KnuYPw/hSsOdh7DPdan1pM4.ZLMohXJDVN.EhdZlcbNOOXw1HbdlH0nL2gbSc2x2I/YubPvZ8sSM7pm3C/.5U.' \
         --uid ${USER_ID} \
         --gid ${USER_GID} \
         --shell /bin/bash \
         --create-home \
         --system \
         ${USER}; "
