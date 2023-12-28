# IcyReader
## Description

IcyReader is a library that helps you extract what's currently playing on your favorite radio.
All you have to do is provide the stream URL of the radio and IcyReader will read metadata and IcyReader
will do its best to extract metadata regarding the artist and title currently playing

## Usage

All you have to do is build an IcyReader than call its `currentlyPlaying` to receive the information you're looking for
```java
  IcyReader reader = new IcyReader.IcyReaderBuilder("https://stream.url").build();
  SongInfo songInfo = reader.currentlyPlaying();
```

## IcyReaderBuilder

The only mandatory field is the stream URL. Actually, if you're listening the one of the supported Sources (see `dev.migwel.icyreader.Sources`),
you don't even have to provide a URL but can provide the Sources itself.

Next to this, the other parameter you may want to play with is the `IcyStreamTitleParser` as some streams provide the song information in format
`artist - title` while other do `title - artist`. Thus you may want to either use `StreamTitleArtistFirstParser` (default) or `StreamTitleArtistSecondParser`

# SongInfo

What you receive from the call to `currentlyPlaying` is a SongInfo record:
```java
public record SongInfo(String rawData, String artist, String title) {}
```

`artist` and `title` are self-explanatory. `rawData` is what was read from the stream. It can especially be useful when artist and title could not be parsed
from that raw information.

## Maven dependency
If you want to use this library, you can import it to your project using
```xml
		<dependency>
			<groupId>dev.migwel.icyreader</groupId>
			<artifactId>IcyReader</artifactId>
			<version>1.1-RELEASE</version>
		</dependency>
```
