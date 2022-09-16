package com.example.cinemaflix.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemaflix.*
import com.example.cinemaflix.models.Actor
import kotlinx.android.synthetic.main.actor_item.view.*


class ActorAdapter(
    private val actors : List<Actor>
) : RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    class ActorViewHolder(view : View) :RecyclerView.ViewHolder(view) {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/original/"
        fun bindActor(actor: Actor) {
            itemView.actorascharname.text = actor.character

            itemView.actorname.text = actor.name
            Glide.with(itemView).load(IMAGE_BASE + actor.profile_path).into(itemView.profilepath)
        }


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorAdapter.ActorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.actor_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bindActor(actors[position])
    }

    override fun getItemCount(): Int = actors.size
}




