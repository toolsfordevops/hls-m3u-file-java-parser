package io.t12s.oss.hls.playlist.entity;

import java.util.List;

import io.t12s.oss.hls.playlist.tag.entity.BasicTag;
import lombok.Builder;

@Builder
public class Metadata {
    private final List<BasicTag> playlistTags;

}
