package sunspring;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named
@SessionScoped
public class ThemeBean implements Serializable{

    private String theme = "start";
    private List<String> themes;    
    private String locale;

    
	public String getTheme() {		
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public void changeTheme() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if(params.containsKey("globaltheme")) {
			theme = params.get("globaltheme");
		}
    }
	
	public List<String> getThemes() {
		return themes;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {		
		this.locale = locale;
	}

	@PostConstruct
	public void init(){
		locale=FacesContext.getCurrentInstance().getExternalContext().getRequestLocale().toString();
        themes = new ArrayList<String>();
        themes.add("afterdark");
        themes.add("afternoon");
        themes.add("afterwork");
        themes.add("aristo");
        themes.add("black-tie");
        themes.add("blitzer");
        themes.add("bluesky");
        themes.add("bootstrap");
        themes.add("casablanca");
        themes.add("cupertino");
        themes.add("cruze");
        themes.add("dark-hive");
        themes.add("delta");
        themes.add("dot-luv");
        themes.add("eggplant");
        themes.add("excite-bike");
        themes.add("flick");
        themes.add("glass-x");
        themes.add("home");
        themes.add("hot-sneaks");
        themes.add("humanity");
        themes.add("le-frog");
        themes.add("midnight");
        themes.add("mint-choc");
        themes.add("overcast");
        themes.add("pepper-grinder");
        themes.add("redmond");
        themes.add("rocket");
        themes.add("sam");
        themes.add("smoothness");
        themes.add("south-street");
        themes.add("start");
        themes.add("sunny");
        themes.add("swanky-purse");
        themes.add("trontastic");
        themes.add("ui-darkness");
        themes.add("ui-lightness");
        themes.add("vader");
	}
	
	@PreDestroy
	public void destory(){
		
	}

}
