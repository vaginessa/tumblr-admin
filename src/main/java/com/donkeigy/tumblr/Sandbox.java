package com.donkeigy.tumblr;

import com.donkeigy.objects.util.ApplicationInfo;
import com.donkeigy.service.util.OAuthConnection;
import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.User;

//import java.awt.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cedric on 2/20/15.
 */
public class Sandbox
{
    public static void main (String [] args)
    {
        ApplicationInfo info = new ApplicationInfo("pF5upteQMm5SBUFwE0vzDRS3OIqIKOokdfx0odY8aTLg60IkqJ", "iZ08fU69HR6VouBNaajVFF9FkaTW8p1lcG5qTFSDR4kJ1pU589");


        OAuthConnection oAuthConnection = new OAuthConnection();

        oAuthConnection.initService(info);
        String requestUrl = oAuthConnection.retrieveAuthorizationUrl();

        try
        {
            URI uri = new java.net.URI(requestUrl);
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri);

            System.out.println("Please type in verifier code:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String verifier = br.readLine();
            oAuthConnection.retrieveAccessToken(verifier, info);

            JumblrClient client = new JumblrClient(info.getApiKey(),info.getApiSecret());
            client.setToken(oAuthConnection.getAccessToken().getToken(), oAuthConnection.getAccessToken().getSecret());

            // Write the user's name
            User user = client.user();
            System.out.println(user.getName());



            // get the blogs the user is following
            List<Blog> followedBlogs = client.userFollowing(); // List of Blogs user is following

            HashMap<String, Blog> followedBlogMap = new HashMap<String, Blog>(); // Map for searching
            System.out.println("List of blogs "+ user.getName()+" is following.");
            for(Blog followedBlog: followedBlogs)
            {
                System.out.println(followedBlog.getName());
                followedBlogMap.put(followedBlog.getName(), followedBlog);
            }
            // And list their blogs
            for (Blog blog : user.getBlogs()) {
                System.out.println("\t" + blog.getTitle());
                List<User> followers = blog.followers();
                for(User follower : followers)
                {
                    System.out.println(follower.getName() + " is now being passed Judgement.");

                        if(!follower.isFollowing())
                        {

                            System.out.println("Now Following "+follower.getName());
                            client.follow(follower.getName()+".tumblr.com"); //follow who has been following u
                        }


                }
            }

            // Like the most recent "lol" tag
            client.tagged("thick asians").get(0).like();
            client.tagged("thick asians").get(0).reblog("thickasianlover");
        }
        catch (Exception e)
        {
            System.out.println("Problem with getting accessing url.");
            e.printStackTrace();
        }
    }
}
