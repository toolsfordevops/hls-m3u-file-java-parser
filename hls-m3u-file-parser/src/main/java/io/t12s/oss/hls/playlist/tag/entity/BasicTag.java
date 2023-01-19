package io.t12s.oss.hls.playlist.tag.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class BasicTag {
    private BasicTagName name;
}
