package web.urlSpider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlResponseInfo {

    private UrlResponseInfo root;

    private UrlResponseInfo chiled;
    private String url;

    private List list;


}
