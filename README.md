

<H3>Параметры правила парсинга</H3>

content-type=html/xml<br>
channel-tag=верхний уровень для поиска, имя тега (обязателен для xml, не обязателен для html)<br>
channel-className=верхний уровень для поиска, имя класса<br>
article-tag=второй уровень для поиска, имя тега(обязателен для xml, не обязателен для html)<br>
article-class=второй уровень для поиска, имя класса<br>
title-tag=тег поиска title(обязателен для xml, не обязателен для html)<br>
title-class=имя класса поиска title<br>
description-tag=тег поиска description(обязателен для xml, не обязателен для html)<br>
description-class=имя класса поиска description<br>
link-tag=тег для поиска ссылки на новость(не обязателен)<br>
link-class=имя класса для поиска ссылки на новость(не обязателен)<br>
link-attr=атрибут из которого брать ссылку(не обязателен, если не задан то берется текст из тега)<br>
link-domain=ссылка на корень сайта, используется для относительных ссылок на новости и картинки<br>
img-tag=тег для поиска ссылки на картинку(не обязателен)<br>
img-class=имя класса для поиска ссылки на картинку(не обязателен)<br>
img-attr=атрибут из которого брать ссылку на картинку(не обязателен, если не задан то берется текст из тега)<br>


<H3>Примеры сайтов для парсинга </H3>

https://news.mail.ru/society/<br>
content-type=html<br>
channel-tag=div<br>
channel-className=cols<br>
article-class=newsitem<br>
article-tag=div<br>
title-class=newsitem__title<br>
title-tag=a<br>
description-class=newsitem__text<br>
description-tag=div<br>
link-class=link-holder<br>
link-tag=a<br>
link-attr=href<br>
link-domain=https://news.mail.ru<br>
img-class=photo__pic<br>
img-tag=img<br>
img-attr=data-lazy-block-src<br>


https://news.yandex.ru/<br>
content-type=html<br>
channel-tag=a<br>
channel-className=stories-set__item<br>
article-class=story__topic<br>
article-tag=div<br>
title-class=story__title<br>
title-tag=a<br>
description-class=story__text<br>
description-tag=div<br>
link-class=link<br>
link-tag=a<br>
link-attr=href<br>
link-domain=https://yandex.ru<br>
img-class=image<br>
img-tag=img<br>
img-attr=src<br>


https://news.yandex.ru/health.rss<br>
content-type=xml<br>
channel-tag=channel<br>
article-tag=item<br>
title-tag=title<br>
description-tag=description<br>
datePublished-tag=pubDate<br>
link-tag=link<br>
link-domain=https://news.yandex.ru/<br>

