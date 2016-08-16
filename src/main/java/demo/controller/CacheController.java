package demo.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Status;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.statistics.StatisticsGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CacheController {

	@Autowired
	private CacheManager cacheManager;

	@RequestMapping("/getCacheDetail")
	public ModelAndView getCacheDetail(Model model, HttpServletRequest request) {

		Collection<String> cacheNames = cacheManager.getCacheNames();
		for (final String cacheName : cacheNames) {
			Cache cache = (Cache) cacheManager.getCache(cacheName);
			if (null != cache && Status.STATUS_ALIVE.equals(cache.getStatus())) {
	            final CacheConfiguration cacheConfiguration = cache.getCacheConfiguration();
	   	        final StatisticsGateway statistics = cache.getStatistics();
	   	        
	        }
		}

		ModelAndView cacheDetail = new ModelAndView("cacheDetail");
		cacheDetail.addObject("cacheDetail", cacheNames);
		return cacheDetail;
	}

}
