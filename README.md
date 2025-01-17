# jMISB

![Build Status](https://github.com/WestRidgeSystems/jmisb/actions/workflows/jdk11.yml/badge.svg)
[![codecov](https://codecov.io/gh/WestRidgeSystems/jmisb/branch/develop/graph/badge.svg?token=SWXQJKERQY)](https://codecov.io/gh/WestRidgeSystems/jmisb)
[![CodeQL](https://github.com/WestRidgeSystems/jmisb/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/WestRidgeSystems/jmisb/actions/workflows/codeql-analysis.yml)
[![Maven Central](https://maven-badges-generator.herokuapp.com/maven-central/org.jmisb/jmisb/badge.svg)](https://maven-badges-generator.herokuapp.com/maven-central/org.jmisb/jmisb)
[![Gitter](https://badges.gitter.im/jmisb/community.svg)](https://gitter.im/jmisb/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

## About

jMISB is an open source Java library implementing various
[MISB](https://gwg.nga.mil/gwg/focus-groups/misb/ "MISB home page") standards.
It leverages the excellent work by
[bytedeco](https://github.com/bytedeco) on bringing video support to Java.
Stay tuned here for updates, and please join us on [gitter](https://gitter.im/jmisb/community) if you need help
or would like to participate!

## Why jMISB?

The Motion Imagery Standards Board, or MISB's mission is to develop and
maintain standards for interoperability between motion imagery systems in use
within the Department of Defense (DoD) and Intelligence Community (IC).

The goal of the jMISB project is to provide an open implementation of
these standards and allow government and industry to leverage them more easily
and effectively. The jMISB project is not affiliated with, nor endorsed by
MISB in any way.

## Scope

The MISB has been quite prolific in creation of new standards since its
inception in 2000. As of April 2021, over four dozen standards are listed
on its web site. While the scope of the jMISB project is to support as many of
these standards as possible, the initial focus will be on those
in most widespread use.

The table below lists the status of currently-supported standards:

| Identifier | Name                                                                            | Implementation Status                                                                                              | Known Issues                                                                                                             |
| ---------- | ----                                                                            | ---------------------                                                                                              | ------------                                                                                                             |
| ST 0102    | Security Metadata Universal and Local Sets for Digital Motion Imagery           | Implemented as of ST 0102.12. There is read-only support for some tags (not UMID) that were removed in ST 0102.12. |                                                                                                                          |
| EG 0104    | Predator UAV Basic Universal Metadata Set                                       | Read only support for EG 0104.5. Writing is not planned, since this metadata set is deprecated by MISB.            |                                                                                                                          |
| ST 0601    | UAS Datalink Local Set                                                          | Mostly implemented as of ST 0601.17.                                                                               | [#140](https://github.com/WestRidgeSystems/jmisb/issues/140)                                                              |
| ST 0603    | MISP Time System and Timestamps                                                 | Partly implemented as of ST 0603.5.                                                                                | [#97](https://github.com/WestRidgeSystems/jmisb/issues/97)                                                                |
| ST 0604    | Timestamps for Class 1 / Class 2 Motion Imagery                                 | Partly implemented as of ST 0604.6.                                                                                | [#102](https://github.com/WestRidgeSystems/jmisb/issues/102)                                                              |
| ST 0805    | KLV to Cursor-on-Target (CoT) Conversions                                       | Implemented as of ST 0805.1. Interoperability testing with FalconView and CoT Debug Tool.                          |                                                                                                                          |
| ST 0806    | Remote Video Terminal Metadata Set                                              | Implemented as of ST 0806.5. Unit tests only, no interoperability testing.                                         |                                                                                                                          |
| ST 0808    | Ancillary Text Metadata Sets                                                    | Implemented as of ST 0808.2. Local Set support only, no universal set support. Deprecated by MISB.                 |                                                                                                                          |
| ST 0903    | Video Moving Target Indicator and Track Metadata                                | VMTI and VTrack Local Sets implemented as of ST 0903.5. We also support pre-ST0903.4 files.                        |                                                                                                                          |
| ST 1108    | Motion Imagery Interpretability and Quality Metadata                            | Implemented as of ST 1108.3. ST 1108.2 and earlier is also supported. No interoperability testing.                 |                                                                                                                          |
| ST 1201    | Floating Point to Integer Mapping                                               | Fully implemented per ST 1201.5.                                                                                   |                                                                                                                          |
| ST 1204    | Motion Imagery Identification System (MIIS) Core Identifier                     | Implemented as of ST 1204.3.                                                                                       |                                                                                                                          |
| ST 1206    | Synthetic Aperture Radar (SAR) Motion Imagery Metadata                          | Implemented as of ST 1206.1. Unit tests only, no interoperability testing.                                         |                                                                                                                          |
| ST 1303    | Multi-Dimensional Array Pack (MDAP)                                             | Partly implemented as of ST 1303.2. Only formats and dimensions known to be used are available. Limited testing.   | [#198](https://github.com/WestRidgeSystems/jmisb/issues/198)                                                              |
| ST 1402    | MPEG-2 Transport Stream for Class 1/Class 2 Motion Imagery, Audio, and Metadata | Mostly implemented, support for Sync and Asynchronous multiplexing.                                                |                                                                                                                          |
| ST 1902    | Motion Imagery Metadata (MIMD): Model-to-KLV Transmutation Instructions         | Implemented as of ST 1902.1. No interoperability testing.                                                          |                                                                                                                          |
| ST 1903    | Motion Imagery Metadata (MIMD): Model                                           | Implemented as of ST 1903.1. No interoperability testing.                                                          |                                                                                                                          |
| ST 1904    | Motion Imagery Metadata (MIMD): Base Attributes                                 | Implemented as of ST 1904.1. No interoperability testing.                                                          |                                                                                                                          |
| ST 1905    | Motion Imagery Metadata (MIMD): Platform                                        | Implemented as of ST 1905.1. No interoperability testing.                                                          |                                                                                                                          |
| ST 1906    | Motion Imagery Metadata (MIMD): Staging System                                  | Implemented as of ST 1906.1. No interoperability testing.                                                          |                                                                                                                          |
| ST 1907    | Motion Imagery Metadata (MIMD): Payload                                         | Implemented as of ST 1907.1. No interoperability testing.                                                          |                                                                                                                          |
| ST 1908    | Motion Imagery Metadata (MIMD): Imager System                                   | Implemented as of ST 1908.1. No interoperability testing.                                                          |                                                                                                                          |
| ST 1909    | Metadata Overlay for Visualization                                              | Mostly implemented as of ST 1909.1. No support for Frame Time, next zoom or the reticle.                           | [#97](https://github.com/WestRidgeSystems/jmisb/issues/97)                                                                |

jMISB aims to be cross-platform to run on any modern operating system. However,
since efficient video coding tends to leverage natively-compiled binaries, currently
platform support is limited to Linux, Windows, and MacOS. Android is next on our roadmap
(see [#253](https://github.com/WestRidgeSystems/jmisb/issues/253)).

## Including in Your Project

If you are using a dependency management tool such as Maven with access to the
[Central Repository](https://search.maven.org/), you can configure it to use
jMISB as a dependency. For Maven, add the following to your `pom.xml`:

```xml
    <dependency>
        <groupId>org.jmisb</groupId>
        <artifactId>jmisb-api</artifactId>
        <version>1.11.0</version>
    </dependency>
```

For Gradle, include the following:

```groovy
dependencies {
    implementation 'org.jmisb:jmisb-api:1.11.0'
}
```

## API Usage

A primary objective of jMISB is to provide an easy-to-use API allowing
non-domain experts to create applications leveraging MISB standards.

The primary API for reading/writing video and metadata is in the `org.jmisb.api` package.
See the [javadocs](https://westridgesystems.github.io/jmisb) for an extensive API
reference.

Below is a simple example of reading a network stream containing video
and (optionally) metadata.

```java
        try (IVideoStreamInput stream = new VideoStreamInput())
        {
            stream.open("udp://127.0.0.1:35800");
            stream.addFrameListener(new ExampleProcessor());
            stream.addMetadataListener(new ExampleProcessor());
            while (stream.isOpen()) {
                Thread.sleep(1000);
            }
        }
        catch (IOException e) {
            System.out.println("Could not open the stream");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
```

The `ExampleProcessor` class simply needs to implement the
`IVideoListener` and `IMetadataListener` interfaces
to receive video and metadata asynchronously as the data arrives.

```java
class ExampleProcessor implements IVideoListener, IMetadataListener
{
    @Override
    public void onVideoReceived(VideoFrame frame)
    {
        BufferedImage image = frame.getImage();
        System.out.println("Center pixel RGB: " +
            image.getRGB(image.getWidth()/2, image.getHeight()/2));
    }

    @Override
    public void onMetadataReceived(MetadataFrame frame)
    {
        IMisbMessage metadata = frame.getMisbMessage();
        if (metadata instanceof UasDatalinkMessage)
        {
            UasDatalinkMessage msg = (UasDatalinkMessage)metadata;
            System.out.println("Sensor position: " +
                msg.getField(UasDatalinkTag.SensorLatitude).getDisplayableValue() +
                ", " +
                msg.getField(UasDatalinkTag.SensorLongitude).getDisplayableValue());
        }
        else if (metadata instanceof SecurityMetadataMessage)
        {
            // ...
        }
    }
}
```

The result of `msg.getField(UasDatalinkTag.SensorLatitude)` will be an instance
of the `SensorLatitude` class (implementing `IUasDatalinkValue`).

For more complete examples of usage, see the [examples](./examples) directory,
as well as [viewer](viewer), a Java Swing-based tool for displaying video and metadata.

## Elevation

While not a core focus, jMISB provides some elevation / terrain related support. This includes:

- EGM 96 conversion of altitude between ellipsoid (aka HAE) and geoidal (aka MSL).

## Building

To build the library from the command line, simply run the Maven command:

```sh
mvn install
```

This will compile the source code, run unit tests, and install the JARs to your local Maven repository.

To get started, you may want to run `jmisb-viewer` and experiment
with some test data. This is a sample application intended mainly to aid in
development. To run it from the command line, issue:

```sh
cd viewer
mvn exec:exec
```

## Versioning

jMISB adheres to *semantic versioning* to communicate to client
developers about the scope of changes in any new release. Version numbers
are formatted as `major.minor.patch`, where:

1. The major number is incremented to indicate incompatible API changes.
2. The minor number is incremented to indicate new functionality has been
added, but in a backward-compatible manner.
3. The patch number is incremented to indicate a backwards-compatible bug
fix.

In other words, users of the library should feel comfortable updating to use
a new version unless the major number has changed. In general, users should
keep up to date with the latest patch release for a given
`major.minor` release branch.

Use of -SNAPSHOT within the version number indicates that the version is
for internal development only, i.e., the artifact is not to be used in a
production environment.
