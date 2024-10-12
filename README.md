
#===========================
#dependencyResolutionManagement {
#		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
#		repositories {
#			mavenCentral()
#			maven { url 'https://jitpack.io' }
#		}
#	}
#===========================

#dependencies {
#	       implementation 'com.github.mendop:jg-viewpage:1.0.2'
#	}

#===========================
# private List<IconBean> data = new ArrayList<>();

#===========================
#	  jgView.setActivity(this)
#                .setBackPadding(0, 10, 0, 10)
#                .setItemCount(9)
#               .setSpanCount(3)
#               .setData(data, new IIconAdapterListener<IconBean, IconHolder>() {
#                   @Override
#                   public IconHolder getHoler(ViewGroup parent) {
#                       // *******IconHolder extends RecyclerView.ViewHolder
#                       // *******IconBean implements Serializable 
#                       return new IconHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_icon, parent, false));
#                   }
#                   @Override
#                   public void onBindViewHolder(@NonNull IconHolder holder, int position, List<IconBean> datas) {
#                       holder.itemView.setOnClickListener(new View.OnClickListener() {
#                           @Override
#                           public void onClick(View v) {
#                               Toast.makeText(TestActivity.this, datas.get(position).getTitle(), Toast.LENGTH_SHORT).show();
#                           }
#                       });
#                       holder.iv.setImageResource(datas.get(position).getRes());
#                       holder.title.setText(datas.get(position).getTitle());
#                   }
#               });
# 